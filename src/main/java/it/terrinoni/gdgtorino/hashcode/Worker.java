/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.terrinoni.gdgtorino.hashcode;

import it.terrinoni.gdgtorino.hashcode.io.InputData;
import it.terrinoni.gdgtorino.hashcode.io.OutputData;
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
        }

        // First algorithm
       

        //utils.writer(outputData); // write output data into file

        LOGGER.debug("Main algorithm completed");
    }

}
