/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.terrinoni.gdgtorino.hashcode.model;

/**
 *
 * @author Marco Terrinoni <marco.terrinoni@consoft.it>
 */
public class Slice {

    public int xRow;
    public int xColumn;
    public int yRow;
    public int yColumn;

    public Slice (int xRow, int xColumn, int yRow, int yColumn) {
        this.xRow = xRow;
        this.xColumn = xColumn;
        this.yRow = yRow;
        this.yColumn = yColumn;
    }

    @Override
    public String toString () {
        return "Slice{" + "xRow=" + xRow + ", xColumn=" + xColumn + ", yRow=" + yRow + ", yColumn="
                + yColumn + '}';
    }

}
