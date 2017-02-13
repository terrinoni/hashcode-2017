/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.terrinoni.gdgtorino.hashcode;

import it.terrinoni.gdgtorino.hashcode.io.InputData;
import it.terrinoni.gdgtorino.hashcode.io.OutputData;
import it.terrinoni.gdgtorino.hashcode.model.Slice;
import it.terrinoni.gdgtorino.hashcode.utils.Utility;
import java.util.ArrayList;
import java.util.List;
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
    private List<Slice> slicesList = new ArrayList<>();

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
        }

        char[][] matrix = inputData.getDataMatrix();

        // First algorithm
        int edgeSize = (int) Math.floor(Math.sqrt(inputData.maxNumCellsSlice));

        for (int i = 0; i <= inputData.getNumRows() - edgeSize; i += edgeSize) {
            for (int j = 0; j <= inputData.getNumColumns() - edgeSize;) {
                int numMush = 0;
                int numTom = 0;
                boolean isOk = false;
                for (int k = 0; k < edgeSize && !isOk; k++) {
                    for (int l = 0; l < edgeSize && !isOk; l++) {
                        if (matrix[i + k][j + l] == 'T') {
                            numTom++;
                        } else {
                            numMush++;
                        }
                        if (numMush >= inputData.minNumIngredientsPerSlice && numTom
                                >= inputData.minNumIngredientsPerSlice) {
                            Slice s = new Slice(i, j, i + edgeSize - 1, j + edgeSize - 1);
                            slicesList.add(s);
                            LOGGER.debug("Slice found: {}", s.toString());

                            isOk = true;
                        }
                    }
                }
                if (isOk) {
                    j += edgeSize;
                } else {
                    j++;
                }
            }
        }

        outputData = new OutputData(inputData.getNumRows());
        //utils.writer(outputData); // write output data into file

        LOGGER.debug("Main algorithm completed");
    }

}
