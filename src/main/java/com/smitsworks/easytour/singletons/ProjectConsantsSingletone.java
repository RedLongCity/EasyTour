package com.smitsworks.easytour.singletons;

import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.requestcommands.RequestCommand;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
    
    private TimeUnit shortUpdatingDelay;//Previous or Current update time
    
    private TimeUnit globalUpdatingDelay;//delay between global updating
    
    private List<RequestCommand> requestsPull;//pull for all requests
    
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

    public TimeUnit getShortUpdatingDelay() {
        return shortUpdatingDelay;
    }

    public void setShortUpdatingDelay(TimeUnit shortUpdatingDelay) {
        this.shortUpdatingDelay = shortUpdatingDelay;
    }

    public TimeUnit getGlobalUpdatingDelay() {
        return globalUpdatingDelay;
    }

    public void setGlobalUpdatingDelay(TimeUnit globalUpdatingDelay) {
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

    
    @Override
    public String toString() {
        return "ProjectConsantsSingletone{" + "filtersUpdate=" + filtersUpdate + ", requestUpdating=" + requestUpdating + ", shortUpdatingDelay=" + shortUpdatingDelay + ", globalUpdatingDelay=" + globalUpdatingDelay + ", requestsPull=" + requestsPull + '}';
    }
    
}
