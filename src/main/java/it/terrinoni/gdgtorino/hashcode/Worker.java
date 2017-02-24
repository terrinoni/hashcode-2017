/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.terrinoni.gdgtorino.hashcode;

import it.terrinoni.gdgtorino.hashcode.io.InputData;
import it.terrinoni.gdgtorino.hashcode.io.OutputData;
import it.terrinoni.gdgtorino.hashcode.model.Request;
import it.terrinoni.gdgtorino.hashcode.model.Video;
import it.terrinoni.gdgtorino.hashcode.utils.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Worker class; it contains all the custom logics and the main method.
 *
 * @author Marco Terrinoni <marco.terrinoni@gmail.com>
 */
@Component
public class Worker {

    private final Logger LOGGER = LoggerFactory.getLogger(Worker.class);

    @Value("${input.filename}")
    private String filename;

    private InputData inputData;
    private OutputData outputData;
    private final Utility utils;

    public Worker () {
        utils = Utility.getInstance();
    }

    /**
     * Main method to be executed.
     */
    public void execute () {
        LOGGER.debug("Main execution starts for '{}' file", filename);

        inputData = utils.reader(filename); // read input data from file
        if (inputData == null) {
            throw new RuntimeException("Unable to load input file");
        } else {
            //LOGGER.debug("Input data: {}", inputData.toString());
        }

        //Collections.sort(inputData.videosList, (Video lhs, Video rhs) -> lhs.size - rhs.size);
        LOGGER.debug(inputData.videosList.toString());

        int[][] videosPerEndpoint = new int[inputData.numVideos][inputData.numEndpoints];
        for (int i = 0; i < inputData.numVideos; i++) {
            for (int j = 0; j < inputData.numEndpoints; j++) {
                videosPerEndpoint[i][j] = 0;
            }
        }
        for (Request req : inputData.requestsList) {
            try {
                videosPerEndpoint[req.videoId][req.endpointId] = req.numRequests;
            } catch (Exception e) {
                LOGGER.error("arrivato a " + req.videoId + " - " + req.endpointId);
            }
        }

        outputData = new OutputData();
        outputData.numCaches = inputData.numCaches;
        outputData.videoPerCache = new boolean[inputData.numCaches][inputData.numVideos];
        for (Video v : inputData.videosList) {
            if (v.size > inputData.cacheSize) {
                continue;
            }

            int maxScore = -1;
            int maxScoreIndexCache = -1;
            for (int i = 0; i < inputData.numCaches; i++) {
                int score = 0;
                for (int j = 0; j < inputData.numEndpoints; j++) {
                    if (videosPerEndpoint[v.id][j] > 0 && inputData.connections[i][j] > 0) {
                        score++;
                    }
                }
                if (score > maxScore) {
                    maxScore = score;
                    maxScoreIndexCache = i;
                }
            }

            int sizeCount = 0;
            for (int i = 0; i < inputData.numVideos; i++) {
                if (outputData.videoPerCache[maxScoreIndexCache][i]) {
                    sizeCount += inputData.videosList.get(i).size;
                }
            }
            if (sizeCount + v.size < inputData.cacheSize) {
                outputData.videoPerCache[maxScoreIndexCache][v.id] = true;
            }
        }

        LOGGER.debug("out is {}", outputData);

        utils.writer(outputData, inputData.numVideos); // write output data into file
        LOGGER.debug("Main algorithm completed");
    }

}
