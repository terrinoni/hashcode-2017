/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.terrinoni.gdgtorino.hashcode.io;

/**
 * Wrapper class for output data.
 * This class contains all the information ready to be written in output files, wrapping data in a
 * unique object.
 *
 * @author Marco Terrinoni <marco.terrinoni@gmail.com>
 */
public class OutputData {

    private int totalSlices; // 0 <= S <= R * C

    public OutputData () {
    }

    public OutputData (int totalSlices) {
        this.totalSlices = totalSlices;
    }

    public int getTotalSlices () {
        return totalSlices;
    }

    public void setTotalSlices (int totalSlices) {
        this.totalSlices = totalSlices;
    }

    @Override
    public String toString () {
        return "OutputData{" + "totalSlices=" + totalSlices + '}';
    }

}
