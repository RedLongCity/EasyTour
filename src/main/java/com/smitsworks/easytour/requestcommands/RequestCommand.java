package com.smitsworks.easytour.requestcommands;

import java.sql.Timestamp;

/**
 *
 * @author redlongcity
 * 10.09.2017
 * interface for generalizations requests
 */
public interface RequestCommand {
    
    void execute();
    
    void parse();
    
    void setPriority(Integer priority);
    
    Integer getPriority();
    
    void setDone(Boolean done);
    
    Boolean getDone();
    
    void setByHuman(Boolean byHuman);
    
    Boolean getByHuman();
    
    void setAreNew(Boolean areNew);
    
    Boolean getAreNew();
    
    void setRequestTime(Timestamp time);
    
    Timestamp getRequestTime();
}
