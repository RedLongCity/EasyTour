package com.smitsworks.easytour.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.smitsworks.easytour.JsonView.RequestView;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
    
    @JsonView(RequestView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="country_id", nullable=true)
    private Country country;
    
    @JsonView(RequestView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="from_city_id", nullable=true)
    private From_Cities from_Cities;
    
    @JsonView(TourView.class)
    @NotEmpty
    @Column(name="hotel_rating",unique=false,nullable=false)
    private String hotel_Rating;
    
    @JsonView(TourView.class)
    @Column(name="night_from",unique=false,nullable=false)
    private Integer night_From;
    
    @JsonView(TourView.class)
    @Column(name="night_till",unique=false,nullable=false)
    private Integer night_Till;
    
    @JsonView(RequestView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="meal_type_id",unique=false,nullable=true)
    private Meal_Type meal_Type;
    
    @OneToMany(fetch=FetchType.LAZY,mappedBy="request",cascade=CascadeType.ALL)
    private Set<RequestPullElement> requestPullElement = new HashSet<RequestPullElement>();

    @ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinTable(name="requests_has_tours",
            joinColumns={@JoinColumn(name="request_id")},
            inverseJoinColumns={@JoinColumn(name="tour_id")})
    private Set<Tour> tourSet = new HashSet<Tour>();
    
    @JsonView(RequestView.class)
    @Column(name="request_delay",unique=false,nullable=true)
    private Long requestDelay;

    
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

    public String getHotel_Rating() {
        return hotel_Rating;
    }

    public void setHotel_Rating(String hotel_Rating) {
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

    public Set<RequestPullElement> getRequestPullElement() {
        return requestPullElement;
    }

    public void setRequestPullElement(Set<RequestPullElement> requestPullElement) {
        this.requestPullElement = requestPullElement;
    }

    public Set<Tour> getTourSet() {
        return tourSet;
    }

    public void setTourSet(Set<Tour> tourSet) {
        this.tourSet = tourSet;
    }

    public Long getRequestDelay() {
        return requestDelay;
    }

    public void setRequestDelay(Long requestDelay) {
        this.requestDelay = requestDelay;
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
        return "Request{" + "id=" + id + ", country=" + country + ", from_Cities=" + from_Cities + ", hotel_Rating=" + hotel_Rating + ", night_From=" + night_From + ", night_Till=" + night_Till + ", meal_Type=" + meal_Type + ", requestPullElement=" + requestPullElement + ", tourSet=" + tourSet + ", requestDelay=" + requestDelay + '}';
    }

}
