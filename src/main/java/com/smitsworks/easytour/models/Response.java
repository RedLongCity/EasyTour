package com.smitsworks.easytour.models;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author redlongcity
 * 14/09/2017
 * class for incapsulating ItTours Hot Search response to client
 */
public class Response {
    
    private Long comeBackDelay;
    private List<Tour> tourList;
    private Request request;

    public Response(Long comeBackDelay, List<Tour> tourList) {
        this.comeBackDelay = comeBackDelay;
        this.tourList = tourList;
    }

    public Long getComeBackDelay() {
        return comeBackDelay;
    }

    public void setComeBackDelay(Long comeBackDelay) {
        this.comeBackDelay = comeBackDelay;
    }

    public List<Tour> getTourList() {
        return tourList;
    }

    public void setTourList(List<Tour> tourList) {
        this.tourList = tourList;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.comeBackDelay);
        hash = 59 * hash + Objects.hashCode(this.tourList);
        hash = 59 * hash + Objects.hashCode(this.request);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Response other = (Response) obj;
        if (!Objects.equals(this.comeBackDelay, other.comeBackDelay)) {
            return false;
        }
        if (!Objects.equals(this.tourList, other.tourList)) {
            return false;
        }
        if (!Objects.equals(this.request, other.request)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Response{" + "comeBackDelay=" + comeBackDelay + ", tourList=" + tourList + ", request=" + request + '}';
    }
    
    
    
}
