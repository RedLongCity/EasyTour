package com.smitsworks.easytour.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.smitsworks.easytour.JsonView.TourView;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author redlongcity
 * 13.09.2017
 * model for restoring updating periods
 */

@Entity
@Table(name="updating_sessions")
public class UpdateSession {
    
    @JsonView(TourView.class)
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="session_id",nullable=false,unique=true)
    private Integer id;
    
    @JsonView(TourView.class)
    @Column(name="session_time",nullable=false,unique=false)
    private Timestamp sessionTime;
    
    @JsonView(TourView.class)
    @OneToMany(fetch=FetchType.LAZY,mappedBy="updateSession",cascade=CascadeType.ALL)
    private Set<RequestPullElement> requestPullElementSet = new HashSet<RequestPullElement>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(Timestamp sessionTime) {
        this.sessionTime = sessionTime;
    }

    public Set<RequestPullElement> getRequestPullElementSet() {
        return requestPullElementSet;
    }

    public void setRequestPullElementSet(Set<RequestPullElement> requestPullElementSet) {
        this.requestPullElementSet = requestPullElementSet;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UpdateSession other = (UpdateSession) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UpdateSession{" + "id=" + id + ", sessionTime=" + sessionTime + ", requestPullElementSet=" + requestPullElementSet + '}';
    }

}
