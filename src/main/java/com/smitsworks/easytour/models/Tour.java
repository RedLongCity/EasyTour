package com.smitsworks.easytour.models;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 *
 * @author redlongcity
 */
@Entity
@Table(name="tours")
public class Tour {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tour_id",unique=true,nullable=false)
    private Integer id;
    
    @Column(name="key",unique=false,nullable=false)
    private String key;
    
    @Column(name="type",unique=false,nullable=false)
    private Integer type;
    
    @Column(name="country_id",unique=false,nullable=false)
    private Integer country_id;
    
    @Column(name="country",unique=false,nullable=false)
    private String country;
    
    @Column(name="region",unique=false,nullable=false)
    private String region;
    
    @Column(name="hotel_id",unique=false,nullable=false)
    private Integer hotel_id;
    
    @Column(name="hotel",unique=false,nullable=false)
    private String hotel;
    
    @Column(name="hotel_rating",unique=false,nullable=false)
    private String hotel_rating;
    
    @Column(name="meal_type",unique=false,nullable=false)
    private String meal_Type;
    
    @Column(name="meal_typefull",unique=false,nullable=false)
    private String meal_TypeFull;
    
    @Column(name="room_type",unique=false,nullable=false)
    private String room_Type;
    
    @Column(name="adult_amount",unique=false,nullable=false)
    private Integer adult_Amount;
    
    @Column(name="child_amount",unique=false,nullable=false)
    private String child_Amount;
    
    @Column(name="accomodation",unique=false,nullable=false)
    private String accomodation;
    
    @Column(name="duration",unique=false,nullable=false)
    private Integer duration;
    
    @Column(name="date_from",unique=false,nullable=false)// (формат YYYY-mm-dd)
    private String date_From;
    
    @Column(name="date_from_unix",unique=false,nullable=false)
    private Integer date_From_Unix;
    
    @Column(name="currency_id",unique=false,nullable=false)
    private Integer currency_id;
    
    @Column(name="currency_symbol",unique=false,nullable=false)
    private String currency_Symbol;
    
    @OneToMany(fetch=FetchType.LAZY,mappedBy="tour")
    private Set<Price> prices = new HashSet<Price>();
    
    @Column(name="price_old",unique=false,nullable=false)
    private Integer price_Old;
    
    @Column(name="price_change_percent",unique=false,nullable=false)
    private Float price_Change_Percent;
    
    @Column(name="from_city_id",unique=false,nullable=false)
    private Integer from_City_Id;
    
    @Column(name="from_city",unique=false,nullable=false)
    private String from_City;
    
    @Column(name="from_city_gen",unique=false,nullable=false)
    private String from_City_Gen;
    
    @Column(name="transport_type",unique=false,nullable=false)
    private String transport_Type;
    
    @OneToOne(fetch=FetchType.LAZY,mappedBy="tour",cascade=CascadeType.ALL)
    private Hotel_Image hotel_Image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Integer country_id) {
        this.country_id = country_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(Integer hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getHotel_rating() {
        return hotel_rating;
    }

    public void setHotel_rating(String hotel_rating) {
        this.hotel_rating = hotel_rating;
    }

    public String getMeal_Type() {
        return meal_Type;
    }

    public void setMeal_Type(String meal_Type) {
        this.meal_Type = meal_Type;
    }

    public String getMeal_TypeFull() {
        return meal_TypeFull;
    }

    public void setMeal_TypeFull(String meal_TypeFull) {
        this.meal_TypeFull = meal_TypeFull;
    }

    public String getRoom_Type() {
        return room_Type;
    }

    public void setRoom_Type(String room_Type) {
        this.room_Type = room_Type;
    }

    public Integer getAdult_Amount() {
        return adult_Amount;
    }

    public void setAdult_Amount(Integer adult_Amount) {
        this.adult_Amount = adult_Amount;
    }

    public String getChild_Amount() {
        return child_Amount;
    }

    public void setChild_Amount(String child_Amount) {
        this.child_Amount = child_Amount;
    }

    public String getAccomodation() {
        return accomodation;
    }

    public void setAccomodation(String accomodation) {
        this.accomodation = accomodation;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDate_From() {
        return date_From;
    }

    public void setDate_From(String date_From) {
        this.date_From = date_From;
    }

    public Integer getDate_From_Unix() {
        return date_From_Unix;
    }

    public void setDate_From_Unix(Integer date_From_Unix) {
        this.date_From_Unix = date_From_Unix;
    }

    public Integer getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(Integer currency_id) {
        this.currency_id = currency_id;
    }

    public String getCurrency_Symbol() {
        return currency_Symbol;
    }

    public void setCurrency_Symbol(String currency_Symbol) {
        this.currency_Symbol = currency_Symbol;
    }

    public Set<Price> getPrices() {
        return prices;
    }

    public void setPrices(Set<Price> prices) {
        this.prices = prices;
    }

    public Integer getPrice_Old() {
        return price_Old;
    }

    public void setPrice_Old(Integer price_Old) {
        this.price_Old = price_Old;
    }

    public Float getPrice_Change_Percent() {
        return price_Change_Percent;
    }

    public void setPrice_Change_Percent(Float price_Change_Percent) {
        this.price_Change_Percent = price_Change_Percent;
    }

    public Integer getFrom_City_Id() {
        return from_City_Id;
    }

    public void setFrom_City_Id(Integer from_City_Id) {
        this.from_City_Id = from_City_Id;
    }

    public String getFrom_City() {
        return from_City;
    }

    public void setFrom_City(String from_City) {
        this.from_City = from_City;
    }

    public String getFrom_City_Gen() {
        return from_City_Gen;
    }

    public void setFrom_City_Gen(String from_City_Gen) {
        this.from_City_Gen = from_City_Gen;
    }

    public String getTransport_Type() {
        return transport_Type;
    }

    public void setTransport_Type(String transport_Type) {
        this.transport_Type = transport_Type;
    }

    public Hotel_Image getHotel_Image() {
        return hotel_Image;
    }

    public void setHotel_Image(Hotel_Image hotel_Image) {
        this.hotel_Image = hotel_Image;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
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
        final Tour other = (Tour) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tour{" + "id=" + id + ", key=" + key + ", type=" + type + 
                ", country_id=" + country_id + ", country=" + country + 
                ", region=" + region + ", hotel_id=" + hotel_id + 
                ", hotel=" + hotel + ", hotel_rating=" + hotel_rating + 
                ", meal_Type=" + meal_Type + ", meal_TypeFull=" 
                + meal_TypeFull + ", room_Type=" + room_Type + 
                ", adult_Amount=" + adult_Amount + ", child_Amount=" + 
                child_Amount + ", accomodation=" + accomodation + 
                ", duration=" + duration + ", date_From=" + date_From + 
                ", date_From_Unix=" + date_From_Unix + ", currency_id=" +
                currency_id + ", currency_Symbol=" + currency_Symbol + 
                ", prices=" + prices + ", price_Old=" + price_Old + 
                ", price_Change_Percent=" + price_Change_Percent + 
                ", from_City_Id=" + from_City_Id + ", from_City=" + 
                from_City + ", from_City_Gen=" + from_City_Gen + 
                ", transport_Type=" + transport_Type + ", hotel_Image=" + 
                hotel_Image + '}';
    }
    
    
    
}
