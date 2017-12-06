package com.smitsworks.easytour.command.response;

/**
 *
 * @author redlongcity
 * 14/09/2017
 * interface for generation Response configurators
 */
public interface ResponseCommand<T> {
    
    T execute();
    
}
