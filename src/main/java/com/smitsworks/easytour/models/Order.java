package com.smitsworks.easytour.models;

/**
 * Created by redlongcity on 14.10.2017.
 * class for storing data about order
 */

public class Order {

    private UserData data;

    private Integer tourId;

    public Order() {
    }
    
    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    public boolean isEmpty(){
        if (data != null) {
            return data.isEmpty();
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (data != null ? !data.equals(order.data) : order.data != null) return false;
        return tourId != null ? tourId.equals(order.tourId) : order.tourId == null;

    }

    @Override
    public int hashCode() {
        int result = data != null ? data.hashCode() : 0;
        result = 31 * result + (tourId != null ? tourId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "data=" + data +
                ", tourId=" + tourId +
                '}';
    }
}
