/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.terrinoni.gdgtorino.hashcode.io;

/**
 * Wrapper class for input data.
 * This class contains all the information available in input files, wrapping data in a unique
 * object.
 *
 * @author Marco Terrinoni <marco.terrinoni@gmail.com>
 */
public class InputData {

    public int numVideos;
    public int numEndpoints;
    public int numReqDesc;
    public int numCaches;
    public int cacheSize;

    public InputData () {
    }

}
