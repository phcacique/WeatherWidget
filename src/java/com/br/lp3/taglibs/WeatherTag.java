/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.taglibs;

import com.br.lp3.entities.CurrentWeather;
import com.br.lp3.weather.XMLParser;
import java.io.IOException;
import java.io.StringWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import org.w3c.dom.Element;

/**
 *
 * @author cacique
 */
public class WeatherTag extends SimpleTagSupport {

    
    private String city;
    public void setCity(String city) {
        this.city = city;
    }
    
    private String unit;
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    private StringWriter sw = new StringWriter();
    public static final String APPID = "a05eaeea0a6bb51bf8c2809c80805631";
    
    @Override
    public void doTag() throws JspException, IOException {
        System.out.println(unit);
        //UNIT -> metric (celsius) | imperial (fahrenheit) | kelvin
        if(unit==null) unit = "metric";
        
        if(city!=null){
            JspWriter out = getJspContext().getOut();
            
            XMLParser xmlm = new XMLParser();
            String mode = "xml";
            xmlm.openURL("http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+APPID+"&mode="+mode+"&units="+unit);
            System.out.println("http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+APPID+"&mode="+mode+"&units="+unit);

            Element raiz = xmlm.getDoc().getDocumentElement();
            CurrentWeather cw = xmlm.parseWeather(raiz);
            
            
            switch(cw.getTemperatureUnit()){
                case "fahrenheit": unit="ºF"; break;
                case "metric": unit="ºC"; break;
                default: unit="K";
            }
            
            out.println(
                    "<section class='weather_widget'>"
                            + "<img alt='icon' src='http://openweathermap.org/img/w/"+cw.getWeatherIcon()+".png'>"
                            + "<div>"
                            + "<p class='temperature'>"+cw.getTemperature()+" <span class='unit'>"+unit+"</span></p>"
                            + "<p class='city'>"+this.city+"</p>"
                            + "</div>"
                            + "</section>"
            
            );
            
        } 
    }

    
    
    
}
