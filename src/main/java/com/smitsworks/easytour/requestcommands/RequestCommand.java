package com.smitsworks.easytour.requestcommands;

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

}
