/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.terrinoni.gdgtorino.hashcode.model;

/**
 *
 * @author Marco Terrinoni <marco.terrinoni@gmail.com>
 */
public class Video {

    public int id;
    public int size;

    public Video (int id, int size) {
        this.id = id;
        this.size = size;
    }

    @Override
    public int hashCode () {
        int hash = 5;
        hash = 53 * hash + this.id;
        hash = 53 * hash + this.size;
        return hash;
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Video other = (Video) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.size != other.size) {
            return false;
        }
        return true;
    }

}
