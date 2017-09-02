package com.smitsworks.easytour.models;

import java.util.Objects;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotEmpty;
/**
 *
 * @author redlongcity
 */
@Entity
@Table(name="currencys")
public class Currency {
    
    @Id
    @Column(name="currency_id",unique=true,nullable=false)
    private String currency_id;
    
    @NotEmpty
    @Column(name="name",unique=false,nullable=false)
    private String name;

    public String getId() {
        return currency_id;
    }

    public void setId(String id) {
        this.currency_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.currency_id);
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
        if (!Objects.equals(this.currency_id, other.currency_id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Currency{" + "id=" + currency_id + ", name=" + name + '}';
    }
    
    
}
