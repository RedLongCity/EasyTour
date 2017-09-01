package com.smitsworks.easytour.models;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author redlongcity
 */

@Entity
@Table(name="prices")
public class Price {
    
    @Id
    @Column(name="price_id",unique=true,nullable=false)
    private Integer id;
    
    @Column(name="cost",unique=false,nullable=false)
    private Integer cost;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tour_id", nullable=false)
    private Tour tour;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Price other = (Price) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Price{" + "id=" + id + ", cost=" + cost + ", tour=" + tour + '}';
    }
    
    
    
}
