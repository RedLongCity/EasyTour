package com.smitsworks.easytour.command.response;

import com.smitsworks.easytour.models.Response;

/**
 *
 * @author redlongcity
 * 14/09/2017
 * interface for generation Response configurators
 */
public interface ResponseCommand<T> {
    
    T execute();
    
}