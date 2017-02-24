/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.terrinoni.gdgtorino.hashcode.io;

import java.util.Arrays;

/**
 * Wrapper class for output data.
 * This class contains all the information ready to be written in output files, wrapping data in a
 * unique object.
 *
 * @author Marco Terrinoni <marco.terrinoni@gmail.com>
 */
public class OutputData {

    public int numCaches;
    public boolean[][] videoPerCache;

    public OutputData () {
    }

    @Override
    public String toString () {
        return "OutputData{" + "numCaches=" + numCaches + ", videoPerCache=" + Arrays.toString(videoPerCache) + '}';
    }

}
