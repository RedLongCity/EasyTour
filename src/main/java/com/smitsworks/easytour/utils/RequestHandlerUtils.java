package com.smitsworks.easytour.utils;

import com.smitsworks.easytour.models.Request;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

/**
 *
 * @author redlongcity
 * 09.09.2017
 * interface for handling Request
 */
public interface RequestHandlerUtils {
    
    List<Criterion> getCriterionsByRequest(Request request);
    
    String getURLByRequest(Request request);
    
}
