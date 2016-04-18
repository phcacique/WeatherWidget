/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.entities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 1147106
 */
public class City implements Serializable{
    private long id;
    private String name;
    private double latitude;
    private double longitude;
    private String country;
    private Date sunrise;
    private Date sunset;

    public City() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getSunrise() {
        return sunrise;
    }

    public void setSunrise(Date sunrise) {
        this.sunrise = sunrise;
    }

    public Date getSunset() {
        return sunset;
    }

    public void setSunset(Date sunset) {
        this.sunset = sunset;
    }

    @Override
    public String toString() {
        return "City{" + "id=" + id + ", name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + ", country=" + country + ", sunrise=" + sunrise + ", sunset=" + sunset + '}';
    }

    
}
