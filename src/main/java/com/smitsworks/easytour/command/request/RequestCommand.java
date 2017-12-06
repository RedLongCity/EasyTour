package com.smitsworks.easytour.command.request;

import java.sql.Timestamp;

/**
 *
 * @author redlongcity
 * 10.09.2017
 * interface for generalizations requests
 */
public interface RequestCommand {

    void execute();

    void setPriority(Integer priority);

    Integer getPriority();

    void setDone(Boolean done);

    Boolean getDone();

    void setByHuman(Boolean byHuman);

    Boolean getByHuman();

    void setRequestTime(Timestamp time);

    Timestamp getRequestTime();

    void IncreasePriority();
    
    boolean isProcessed();
    
    void setProcessed(boolean processed);
}
