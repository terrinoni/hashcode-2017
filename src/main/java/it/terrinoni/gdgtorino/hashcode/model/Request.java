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
public class Request {

    public int videoId;
    public int endpointId;
    public int numRequests;

    public Request (int videoId, int endpointId, int numRequests) {
        this.videoId = videoId;
        this.endpointId = endpointId;
        this.numRequests = numRequests;
    }

    @Override
    public int hashCode () {
        int hash = 7;
        hash = 79 * hash + this.videoId;
        hash = 79 * hash + this.endpointId;
        hash = 79 * hash + this.numRequests;
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
        final Request other = (Request) obj;
        if (this.videoId != other.videoId) {
            return false;
        }
        if (this.endpointId != other.endpointId) {
            return false;
        }
        if (this.numRequests != other.numRequests) {
            return false;
        }
        return true;
    }

    @Override
    public String toString () {
        return "Request{" + "videoId=" + videoId + ", endpointId=" + endpointId + ", numRequests="
                + numRequests + '}';
    }

}
