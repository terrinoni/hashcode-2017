/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.terrinoni.gdgtorino.hashcode.utils;

import it.terrinoni.gdgtorino.hashcode.io.InputData;
import it.terrinoni.gdgtorino.hashcode.io.OutputData;
import it.terrinoni.gdgtorino.hashcode.model.Endpoint;
import it.terrinoni.gdgtorino.hashcode.model.Request;
import it.terrinoni.gdgtorino.hashcode.model.Video;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * General singleton utility class; it contains all the reusable methods.
 *
 * @author Marco Terrinoni <marco.terrinoni@gmail.com>
 */
public class Utility {

    private final Logger LOGGER = LoggerFactory.getLogger(Utility.class);

    private Utility () {
    }

    public static Utility getInstance () {
        return NewSingletonHolder.INSTANCE;
    }

    /**
     * Nested holder class.
     */
    private static class NewSingletonHolder {

        private static final Utility INSTANCE = new Utility();
    }

    @Value("${output.filename}")
    private String outputFilename;

    /**
     * Reader method.
     *
     * @param inputFilename
     *
     * @return instance of InputData
     */
    public InputData reader (String inputFilename) {
        InputData inputData = new InputData();

        try {
            LOGGER.debug("Initial resources loading...");
            Resource resource = new ClassPathResource(inputFilename); // initialize resource from file name
            InputStream resInputStream = resource.getInputStream(); // extract the inputStream and ...
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(resInputStream)); // ... initialize the bufferedReader
            LOGGER.debug("Input resource loaded!");

            // First line
            String currLine = buffReader.readLine(); // extract first line from file
            String[] sections = currLine.split(" "); // split first line by space
            int numVideos = Integer.parseInt(sections[0]);
            inputData.numVideos = numVideos;
            int numEndpoints = Integer.parseInt(sections[1]);
            inputData.numEndpoints = numEndpoints;
            int numRequests = Integer.parseInt(sections[2]);
            inputData.numReqDesc = numRequests;
            int numCaches = Integer.parseInt(sections[3]);
            inputData.numCaches = numCaches;
            int capacityCaches = Integer.parseInt(sections[4]);
            inputData.cacheSize = capacityCaches;

            List<Video> videosList = new ArrayList<>();
            currLine = buffReader.readLine();
            sections = currLine.split(" "); // split first line by space
            for (int i = 0; i < numVideos; i++) {
                videosList.add(new Video(i, Integer.parseInt(sections[i])));
            }
            inputData.videosList = videosList;

            List<Endpoint> endpointsList = new ArrayList<>();
            int[][] connections = new int[numEndpoints][numCaches];

            for (int i = 0; i < numEndpoints; i++) {
                for (int j = 0; j < numCaches; j++) {
                    connections[i][j] = -1;
                }
            }

            for (int i = 0; i < numEndpoints; i++) {

                currLine = buffReader.readLine();
                sections = currLine.split(" "); // split first line by space

                endpointsList.add(new Endpoint(i, Integer.parseInt(sections[0])));
                int numConnectedCaches = Integer.parseInt(sections[1]);

                for (int j = 0; j < numConnectedCaches; j++) {

                    currLine = buffReader.readLine();
                    sections = currLine.split(" "); // split first line by space
                    int cacheId = Integer.parseInt(sections[0]);
                    int latency = Integer.parseInt(sections[1]);

                    connections[i][cacheId] = latency;
                }
            }
            inputData.endpointsList = endpointsList;
            inputData.connections = connections;

            List<Request> requestsList = new ArrayList<>();
            for (int i = 0; i < numRequests; i++) {

                currLine = buffReader.readLine();
                sections = currLine.split(" "); // split first line by space

                int videoId = Integer.parseInt(sections[0]);
                int endpointId = Integer.parseInt(sections[1]);
                int numReq = Integer.parseInt(sections[2]);

                requestsList.add(new Request(videoId, endpointId, numReq));
            }
            inputData.requestsList = requestsList;

        } catch (IOException | NullPointerException ex) {
            LOGGER.error("Error while reading from input file", ex);
            inputData = null;
        }

        return inputData;
    }

    /**
     * Writer method.
     *
     * @param outputData instance of OutputData
     */
    public void writer (OutputData outputData, int numVideos) {

        PrintStream ps = System.out;

        ps.println(outputData.numCaches);
        for (int i = 0; i < outputData.numCaches; i++) {
            ps.print(i);
            for (int j = 0; j < numVideos; j++) {
                if (outputData.videoPerCache[i][j]) {
                    ps.print(" " + j);
                }
            }
            ps.println();
        }
        ps.close();

    }

}
