package com.smitsworks.easytour.models;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonView;
import com.smitsworks.easytour.JsonView.TourView;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;
/**
 *
 * @author redlongcity
 */

@Entity
@Table(name="requests")
public class Request {

    private static final Logger LOG = Logger.getLogger(Request.class.getName());
    
    @JsonView(TourView.class)
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="request_id",unique=true,nullable=false)
    private Integer id;
    
    @JsonView(TourView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="country_id", nullable=true)
    private Country country;
    
    @JsonView(TourView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="from_city_id", nullable=true)
    private From_Cities from_Cities;
    
    @JsonView(TourView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="hotel_rating_id", nullable=false)
    private Hotel_Rating hotel_Rating;
    
    @JsonView(TourView.class)
    @NotEmpty
    @Column(name="night_from",unique=false,nullable=false)
    private Integer night_From;
    
    @JsonView(TourView.class)
    @NotEmpty
    @Column(name="night_till",unique=false,nullable=false)
    private Integer night_Till;
    
    @JsonView(TourView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="meal_type_id", nullable=false)
    private Meal_Type meal_Type;
    
    @JsonView(TourView.class)
    @NotEmpty
    @Column(name="request_datetime",unique=false,nullable=false)
    private Timestamp request_DateTime;
    
    @OneToMany(fetch=FetchType.LAZY,mappedBy="request",cascade=CascadeType.ALL)
    private Set<RequestPullElement> requestPullElement = new HashSet<RequestPullElement>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public From_Cities getFrom_Cities() {
        return from_Cities;
    }

    public void setFrom_Cities(From_Cities from_Cities) {
        this.from_Cities = from_Cities;
    }

    public Hotel_Rating getHotel_Rating() {
        return hotel_Rating;
    }

    public void setHotel_Rating(Hotel_Rating hotel_Rating) {
        this.hotel_Rating = hotel_Rating;
    }

    public Integer getNight_From() {
        return night_From;
    }

    public void setNight_From(Integer night_From) {
        this.night_From = night_From;
    }

    public Integer getNight_Till() {
        return night_Till;
    }

    public void setNight_Till(Integer night_Till) {
        this.night_Till = night_Till;
    }

    public Meal_Type getMeal_Type() {
        return meal_Type;
    }

    public void setMeal_Type(Meal_Type meal_Type) {
        this.meal_Type = meal_Type;
    }

    public Timestamp getRequest_DateTime() {
        return request_DateTime;
    }

    public void setRequest_DateTime(Timestamp request_DateTime) {
        this.request_DateTime = request_DateTime;
    }

    public Set<RequestPullElement> getRequestPullElement() {
        return requestPullElement;
    }

    public void setRequestPullElement(Set<RequestPullElement> requestPullElement) {
        this.requestPullElement = requestPullElement;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final Request other = (Request) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Request{" + "id=" + id + ", country=" + country + ", from_Cities=" + from_Cities + ", hotel_Rating=" + hotel_Rating + ", night_From=" + night_From + ", night_Till=" + night_Till + ", meal_Type=" + meal_Type + ", request_DateTime=" + request_DateTime + ", requestPullElement=" + requestPullElement + '}';
    }


    
}
