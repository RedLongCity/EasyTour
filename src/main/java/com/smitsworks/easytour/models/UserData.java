package com.smitsworks.easytour.models;

import com.google.common.base.Strings;

/**
 * Created by redlo on 13.10.2017.
 */

public class UserData {

    private String name;

    private String mobileNumber;

    private String email;

    private String city;

    public UserData(String name, String mobileNumber, String email, String city) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isEmpty(){
        return Strings.isNullOrEmpty(name)&&
                Strings.isNullOrEmpty(mobileNumber)&&
                Strings.isNullOrEmpty(email)&&
                Strings.isNullOrEmpty(city);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserData userData = (UserData) o;

        if (name != null ? !name.equals(userData.name) : userData.name != null) return false;
        if (mobileNumber != null ? !mobileNumber.equals(userData.mobileNumber) : userData.mobileNumber != null)
            return false;
        if (email != null ? !email.equals(userData.email) : userData.email != null) return false;
        return city != null ? city.equals(userData.city) : userData.city == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (mobileNumber != null ? mobileNumber.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "name='" + name + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
