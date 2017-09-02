package com.smitsworks.easytour.models;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author redlongcity
 */

@Entity
@Table(name="hotel_images")
public class Hotel_Image {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="full",unique=false,nullable=false)
    private String full;
    
    @Column(name="thumb",unique=false,nullable=false)
    private String thumb;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tour_id", nullable=false)
    private Tour tour;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.id);
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
        final Hotel_Image other = (Hotel_Image) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Hotel_Image{" + "id=" + id + ", full=" + full + ", thumb=" + thumb + ", tour=" + tour + '}';
    }
    
    
}
