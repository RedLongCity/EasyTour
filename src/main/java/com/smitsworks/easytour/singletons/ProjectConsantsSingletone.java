package com.smitsworks.easytour.singletons;

import com.smitsworks.easytour.models.Request;
import java.sql.Timestamp;
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
    
    private boolean filtersUpdate;//consist Country, From_Cities updating etc.
    
    private Request requestUpdating;//Request which are updating at the moment
    
    private Timestamp updateTime;//Previous or Current update time
    
    private TimeUnit updatingDelay;//delay between global updating
    
    
    
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

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public TimeUnit getUpdatingDelay() {
        return updatingDelay;
    }

    public void setUpdatingDelay(TimeUnit updatingDelay) {
        this.updatingDelay = updatingDelay;
    }

    @Override
    public String toString() {
        return "ProjectConsantsSingletone{" + "filtersUpdate=" + filtersUpdate + ", requestUpdating=" + requestUpdating + ", updateTime=" + updateTime + ", updatingDelay=" + updatingDelay + '}';
    }
    
    
}
