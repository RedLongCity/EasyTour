package com.smitsworks.easytour.models;

import com.google.common.base.Strings;
/**
 *
 * @author redlongcity
 * class for storing data about user's feedback
 */


public class FeedBack {

    private String name;

    private String device;

    private String email;

    private String feedBack;

    public FeedBack() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }

    public boolean isEmpty(){
        return Strings.isNullOrEmpty(feedBack);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeedBack feedBack1 = (FeedBack) o;

        if (name != null ? !name.equals(feedBack1.name) : feedBack1.name != null) return false;
        if (device != null ? !device.equals(feedBack1.device) : feedBack1.device != null)
            return false;
        if (email != null ? !email.equals(feedBack1.email) : feedBack1.email != null) return false;
        return feedBack != null ? feedBack.equals(feedBack1.feedBack) : feedBack1.feedBack == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (device != null ? device.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (feedBack != null ? feedBack.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FeedBack{" +
                "name='" + name + '\'' +
                ", device='" + device + '\'' +
                ", email='" + email + '\'' +
                ", feedBack='" + feedBack + '\'' +
                '}';
    }
}

