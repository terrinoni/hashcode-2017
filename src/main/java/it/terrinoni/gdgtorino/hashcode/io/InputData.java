/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.terrinoni.gdgtorino.hashcode.io;

import it.terrinoni.gdgtorino.hashcode.model.Endpoint;
import it.terrinoni.gdgtorino.hashcode.model.Request;
import it.terrinoni.gdgtorino.hashcode.model.Video;
import java.util.List;

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

    public int[][] connections;

    public List<Video> videosList;
    public List<Endpoint> endpointsList;
    public List<Request> requestsList;

    public InputData () {
    }

    @Override
    public String toString () {
        return "InputData{" + "numVideos=" + numVideos + ", numEndpoints=" + numEndpoints
                + ", numReqDesc=" + numReqDesc + ", numCaches=" + numCaches + ", cacheSize="
                + cacheSize + ", connections=" + connections + ", videosList=" + videosList
                + ", endpointsList=" + endpointsList + ", requestsList=" + requestsList + '}';
    }

}
