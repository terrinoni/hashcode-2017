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
public class Endpoint {

    public int id;
    public int latencyDataCenter;

    public Endpoint (int id, int latencyDataCenter) {
        this.id = id;
        this.latencyDataCenter = latencyDataCenter;
    }

    @Override
    public int hashCode () {
        int hash = 5;
        hash = 11 * hash + this.id;
        hash = 11 * hash + this.latencyDataCenter;
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
        final Endpoint other = (Endpoint) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.latencyDataCenter != other.latencyDataCenter) {
            return false;
        }
        return true;
    }

}
