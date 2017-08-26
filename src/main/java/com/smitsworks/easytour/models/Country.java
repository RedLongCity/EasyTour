package com.smitsworks.easytour.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import org.hibernate.validator.constraints.NotEmpty;
/**
 *
 * @author redlongcity
 */

@Entity
@Table(name="countrys")
public class Country {
    
    @Id
    private String id;
    
    @NotEmpty
    @Column(name="name",unique=false,nullable=false)
    private String name;
    
    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "countrys_has_from_cities",
            joinColumns = {@JoinColumn(name = "countrys_id")},
            inverseJoinColumns = {@JoinColumn(name = "from_cities_id")})
    private Set<From_Cities> from_CitiesSet = new HashSet<From_Cities>();

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

    public Set<From_Cities> getFrom_CitiesSet() {
        return from_CitiesSet;
    }

    public void setFrom_CitiesSet(Set<From_Cities> from_CitiesSet) {
        this.from_CitiesSet = from_CitiesSet;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + Objects.hashCode(this.from_CitiesSet);
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
        final Country other = (Country) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.from_CitiesSet, other.from_CitiesSet)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Country{" + "id=" + id + ", name=" + name + ", from_CitiesSet=" + from_CitiesSet + '}';
    }
    
}
