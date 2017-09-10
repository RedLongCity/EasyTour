package com.smitsworks.easytour.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.smitsworks.easytour.JsonView.TourView;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;
/**
 *
 * @author redlongcity
 * 08.09.2017
 * class like element for pull of requests
 */
@Entity
@Table(name="requests_pull")
public class RequestPullElement {

    private static final Logger LOG = Logger.getLogger(RequestPullElement.class.getName());
    
    @JsonView(TourView.class)
    @Id
    @Column(name="requests_pull_id",nullable=false,unique=true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @JsonView(TourView.class)
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="request_id",nullable=false)
    private Request request;
    
    @JsonView(TourView.class)
    @NotEmpty
    @Column(name="request_datetime",unique=false,nullable=false)
    private Timestamp request_pull_DateTime;
    
    @JsonView(TourView.class)
    @Column(name="are_new",nullable=true)
    private Boolean areNew;
    
    @JsonView(TourView.class)
    @Column(name="are_confirmed",nullable=true)
    private Boolean areConfirmed;
    
    @JsonView(TourView.class)
    @NotEmpty
    @Column(name="done",unique=false,nullable=false)
    private Boolean done;
    
    @JsonView(TourView.class)
    @NotEmpty
    @Column(name="priority",unique=false,nullable=false)
    private Integer priority;
    
    @JsonView(TourView.class)
    @NotEmpty
    @Column(name="by_human",unique=false,nullable=false)
    private Boolean byHuman;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Timestamp getRequest_pull_DateTime() {
        return request_pull_DateTime;
    }

    public void setRequest_pull_DateTime(Timestamp request_pull_DateTime) {
        this.request_pull_DateTime = request_pull_DateTime;
    }

    public Boolean getAreNew() {
        return areNew;
    }

    public void setAreNew(Boolean areNew) {
        this.areNew = areNew;
    }

    public Boolean getAreConfirmed() {
        return areConfirmed;
    }

    public void setAreConfirmed(Boolean areConfirmed) {
        this.areConfirmed = areConfirmed;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Boolean getByHuman() {
        return byHuman;
    }

    public void setByHuman(Boolean byHuman) {
        this.byHuman = byHuman;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final RequestPullElement other = (RequestPullElement) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RequestPullElement{" + "id=" + id + ", request=" + request + ", request_pull_DateTime=" + request_pull_DateTime + ", areNew=" + areNew + ", areConfirmed=" + areConfirmed + ", done=" + done + ", priority=" + priority + ", byHuman=" + byHuman + '}';
    }

    
    
}
