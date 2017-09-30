package com.smitsworks.easytour.singletons;

import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.requestcommands.RequestCommand;
import com.smitsworks.easytour.utils.TimeUtils;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    private boolean globalDelay=false;//for regulation access to out database
    
    private boolean filtersUpdate=false;//consist Country, From_Cities updating etc.
    
    private Request requestUpdating;//Request which are updating at the moment
    
    private long shortUpdatingDelay=20000;//Previous or Current update time
    
    private boolean shortRun=true;//indicator for short job running
    
    private boolean shortSuspended=false;//indicator pause job by itself
    
    private String globalUpdatingDelay="* 0/1 * * * ?";//delay between global updating
    
    private boolean globalRun=true;//indicator for global job running
    
    private boolean globalSuspended=false;//indicator of pausing job by itself

    private List<RequestCommand> requestsPull=new ArrayList<RequestCommand>();//pull for all requests
    
    private Timestamp timeOfCurrentSession;
    
    private Timestamp timeOfPreviousSession;
    
    @Autowired
    TimeUtils timeUtils;
    
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

    public long getShortUpdatingDelay() {
        return shortUpdatingDelay;
    }

    public void setShortUpdatingDelay(long shortUpdatingDelay) {
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
        if(this.timeOfCurrentSession==null){
            timeOfCurrentSession = timeUtils.getCurrentTime();
        }
        return timeOfCurrentSession;
    }

    public void setTimeOfCurrentSession(Timestamp timeOfCurrentSession) {
        if(this.timeOfCurrentSession==null){
            timeOfCurrentSession = timeUtils.getCurrentTime();
        }
        this.timeOfCurrentSession = timeOfCurrentSession;
    }

    public Timestamp getTimeOfPreviousSession() {
        if(this.timeOfPreviousSession==null){
            this.timeOfPreviousSession = timeUtils.getCurrentTime();
        }
        return timeOfPreviousSession;
    }

    public void setTimeOfPreviousSession(Timestamp timeOfPreviousSession) {
        if(this.timeOfPreviousSession==null){
            this.timeOfPreviousSession = timeUtils.getCurrentTime();
        }
        this.timeOfPreviousSession = timeOfPreviousSession;
    }

    public boolean isShortRun() {
        return shortRun;
    }

    public void setShortRun(boolean isShorRun) {
        this.shortRun = isShorRun;
    }

    public boolean isGlobalRun() {
        return globalRun;
    }

    public void setGlobalRun(boolean isGlobalRunning) {
        this.globalRun = isGlobalRunning;
    }

    public boolean isShortSuspended() {
        return shortSuspended;
    }

    public void setShortSuspended(boolean shortSuspended) {
        this.shortSuspended = shortSuspended;
    }

    public boolean isGlobalSuspended() {
        return globalSuspended;
    }

    public void setGlobalSuspended(boolean globalSuspended) {
        this.globalSuspended = globalSuspended;
    }
    
    
    
}
