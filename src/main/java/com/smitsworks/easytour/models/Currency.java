package com.smitsworks.easytour.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.smitsworks.easytour.JsonView.TourView;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import org.hibernate.validator.constraints.NotEmpty;
/**
 *
 * @author redlongcity
 */
@Entity
@Table(name="currencys")
public class Currency {
    
    @JsonView(TourView.class)
    @Id
    @Column(name="currency_id",unique=true,nullable=false)
    private String id;

    @JsonView(TourView.class)
    @NotEmpty
    @Column(name="name",unique=false,nullable=false)
    private String name;
    
    @OneToMany(fetch=FetchType.LAZY,mappedBy="currency",cascade=CascadeType.ALL)
    private Set<Price> prices = new HashSet<Price>();

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

    public Set<Price> getPrices() {
        return prices;
    }

    public void setPrices(Set<Price> prices) {
        this.prices = prices;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.name);
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
        final Currency other = (Currency) obj;
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
        return "Currency{" + "id=" + id + ", name=" + name + ", prices=" + prices + '}';
    }

}
