package com.smitsworks.easytour.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.hibernate.validator.constraints.NotEmpty;
/**
 *
 * @author redlongcity
 */

@Entity
@Table(name="from_cities")
public class From_Cities {
    
    @Id
    @Column(name="from_city_id",nullable=false,unique=true)
    private String id;
    
    @NotEmpty
    @Column(name="name",unique=false,nullable=false)
    private String name;
    
    @ManyToMany(mappedBy = "from_CitiesSet")
    @JsonIgnore
    private Set<Country> countrySet = new HashSet<Country>();
    
    @OneToMany(fetch=FetchType.LAZY,mappedBy="from_Cities",cascade=CascadeType.ALL)
    private Set<Tour> tours = new HashSet<Tour>();
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Country> getCountrySet() {
        return countrySet;
    }

    public void setCountrySet(Set<Country> countrySet) {
        this.countrySet = countrySet;
    }

    public Set<Tour> getTours() {
        return tours;
    }

    public void setTours(Set<Tour> tours) {
        this.tours = tours;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.name);
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
        final From_Cities other = (From_Cities) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "From_Cities{" + "id=" + id + ", name=" + name + ", countrySet=" + countrySet + ", tours=" + tours + '}';
    }

}
