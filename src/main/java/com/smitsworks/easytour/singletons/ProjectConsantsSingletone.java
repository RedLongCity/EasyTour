package com.smitsworks.easytour.singletons;

import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.requestcommands.RequestCommand;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 08.09.2017
 * class for synchronization dao processes and responses to clients
 */
@Service
public class ProjectConsantsSingletone {

    private static final Logger LOG = Logger.getLogger(ProjectConsantsSingletone.class.getName());
    
    private static volatile ProjectConsantsSingletone instance;
    
    private boolean globalDelay;//for regulation access to out database
    
    private boolean filtersUpdate;//consist Country, From_Cities updating etc.
    
    private Request requestUpdating;//Request which are updating at the moment
    
    private Integer shortUpdatingDelay=3000;//Previous or Current update time
    
    private String globalUpdatingDelay="0/5 * * * * ?";//delay between global updating
    
    private List<RequestCommand> requestsPull;//pull for all requests
    
    private Timestamp timeOfCurrentSession;
    
    private Timestamp timeOfPreviousSession;
    
    public static ProjectConsantsSingletone getInstance(){
        ProjectConsantsSingletone localInstance = instance;
        if(localInstance==null){
            synchronized (ProjectConsantsSingletone.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new ProjectConsantsSingletone();
                }
            }
        }
        return localInstance;
    }

    public boolean isFiltersUpdate() {
        return filtersUpdate;
    }

    public void setFiltersUpdate(boolean filtersUpdate) {
        this.filtersUpdate = filtersUpdate;
    }

    public Request getRequestUpdating() {
        return requestUpdating;
    }

    public void setRequestUpdating(Request requestUpdating) {
        this.requestUpdating = requestUpdating;
    }

    public Integer getShortUpdatingDelay() {
        return shortUpdatingDelay;
    }

    public void setShortUpdatingDelay(Integer shortUpdatingDelay) {
        this.shortUpdatingDelay = shortUpdatingDelay;
    }

    public String getGlobalUpdatingDelay() {
        return globalUpdatingDelay;
    }

    public void setGlobalUpdatingDelay(String globalUpdatingDelay) {
        this.globalUpdatingDelay = globalUpdatingDelay;
    }

    public List<RequestCommand> getRequestsPull() {
        return requestsPull;
    }

    public void setRequestsPull(List<RequestCommand> requestsPull) {
        this.requestsPull = requestsPull;
    }

    public boolean isGlobalDelay() {
        return globalDelay;
    }

    public void setGlobalDelay(boolean globalDelay) {
        this.globalDelay = globalDelay;
    }

    public Timestamp getTimeOfCurrentSession() {
        return timeOfCurrentSession;
    }

    public void setTimeOfCurrentSession(Timestamp timeOfCurrentSession) {
        this.timeOfCurrentSession = timeOfCurrentSession;
    }

    public Timestamp getTimeOfPreviousSession() {
        return timeOfPreviousSession;
    }

    public void setTimeOfPreviousSession(Timestamp timeOfPreviousSession) {
        this.timeOfPreviousSession = timeOfPreviousSession;
    }
    
    
}
