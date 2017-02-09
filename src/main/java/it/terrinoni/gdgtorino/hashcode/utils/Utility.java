/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.terrinoni.gdgtorino.hashcode.utils;

import it.terrinoni.gdgtorino.hashcode.io.InputData;
import it.terrinoni.gdgtorino.hashcode.io.OutputData;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        InputData inputData;

        try {
            LOGGER.debug("Initial resources loading...");
            Resource resource = new ClassPathResource(inputFilename); // initialize resource from file name
            InputStream resInputStream = resource.getInputStream(); // extract the inputStream and ...
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(resInputStream)); // ... initialize the bufferedReader
            LOGGER.debug("Input resource loaded!");

            // First line
            String currLine = buffReader.readLine(); // extract first line from file
            String[] sections = currLine.split(" "); // split first line by space
            int numRows = Integer.parseInt(sections[0]); // extract rows
            int numColumns = Integer.parseInt(sections[1]); // extract columns
            int minNumIngredients = Integer.parseInt(sections[2]); // extract minimum number of ingredients per slice
            int maxNumCells = Integer.parseInt(sections[3]); // extract maximum number of cells in a slice
            inputData = new InputData(numRows, numColumns, minNumIngredients, maxNumCells);

            // Following lines
            for (int i = 0; i < numRows; i++) {
                currLine = buffReader.readLine(); // extract whole line
                for (int j = 0; j < numColumns; j++) {
                    LOGGER.debug("Reading cell at [{}:{}]: {}", i, j, currLine.charAt(j)); // read char-by-char
                }
            }

            LOGGER.debug("Input acquisition phase completed! {}", inputData);
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
    public void writer (OutputData outputData) {
        try {
            Path path = Paths.get(outputFilename);
            BufferedWriter buffWriter = Files.newBufferedWriter(path);
            buffWriter.write(outputData.toString());
            buffWriter.close();
        } catch (IOException ex) {
            LOGGER.error("Error while writing result to output file", ex);
            throw new RuntimeException();
        }
    }

}
