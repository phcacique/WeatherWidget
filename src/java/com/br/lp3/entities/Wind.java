/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.entities;

import java.io.Serializable;

/**
 *
 * @author 1147106
 */
public class Wind implements Serializable{
    private double speed;
    private String speedName;
    private String gusts;
    private double direction;
    private String directionCode;
    private String directionName;

    public Wind() {
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    
    public String getSpeedName() {
        return speedName;
    }

    public void setSpeedName(String speedName) {
        this.speedName = speedName;
    }

    public String getGusts() {
        return gusts;
    }

    public void setGusts(String gusts) {
        this.gusts = gusts;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public String getDirectionCode() {
        return directionCode;
    }

    public void setDirectionCode(String directionCode) {
        this.directionCode = directionCode;
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    @Override
    public String toString() {
        return "Wind{" + "speed=" + speed + ", speedName=" + speedName + ", gusts=" + gusts + ", direction=" + direction + ", directionCode=" + directionCode + ", directionName=" + directionName + '}';
    }
}
