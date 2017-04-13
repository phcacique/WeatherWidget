package com.br.lp3.weather;

import com.br.lp3.entities.City;
import com.br.lp3.entities.CurrentWeather;
import com.br.lp3.entities.Wind;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author 1147106
 */
public class XMLParser {

    private Document doc;
    private DocumentBuilder db;

    public XMLParser() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void openFile(File file) {
        try {
            doc = db.parse(file);
        } catch (SAXException | IOException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void openURL(String uri) {
        try {
            URL url = new URL(uri);

            //Conexão HTTP
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.0.10", 3128));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(proxy);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            int code = conn.getResponseCode();

            if(code==407){
                System.out.println("Falha na autenticação do proxy");
            }else if (code == 200) {

                //Leitura dos dados
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                //Criação da String com o resultado
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }
                rd.close();

                //Fecha a conexão
                conn.disconnect();

                //String com o conteúdo
                String content = sb.toString();

                //Gerar o doc
                doc = db.parse(new InputSource(new StringReader(content)));
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CurrentWeather parseWeather(Element element) {
        CurrentWeather cw = new CurrentWeather();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //CITY
        Element cityElement = (Element) element.getElementsByTagName("city").item(0);
        long id = Long.parseLong(cityElement.getAttribute("id"));
        String name = cityElement.getAttribute("name");
        Element coord = (Element) cityElement.getElementsByTagName("coord").item(0);
        double longitude = Double.parseDouble(coord.getAttribute("lon"));
        double latitude = Double.parseDouble(coord.getAttribute("lat"));
        String country = cityElement.getElementsByTagName("country").item(0).getTextContent();
        Element sun = (Element) cityElement.getElementsByTagName("sun").item(0);
        Date sunrise = new Date();
        Date sunset = new Date();
        try {
            sunrise = sdf.parse(sun.getAttribute("rise").replace("T", " "));
            sunset = sdf.parse(sun.getAttribute("set").replace("T", " "));
        } catch (ParseException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        City city = new City();
        city.setCountry(country);
        city.setId(id);
        city.setLatitude(latitude);
        city.setLongitude(longitude);
        city.setName(name);
        city.setSunrise(sunrise);
        city.setSunset(sunset);

        cw.setCity(city);

        //TEMPERATURE
        Element temperatureElement = (Element) element.getElementsByTagName("temperature").item(0);
        double temperature = Double.parseDouble(temperatureElement.getAttribute("value"));
        double temperatureMin = Double.parseDouble(temperatureElement.getAttribute("min"));
        double temperatureMax = Double.parseDouble(temperatureElement.getAttribute("max"));
        String tempreatureUnit = temperatureElement.getAttribute("unit");

        cw.setTemperature(temperature);
        cw.setTemperatureMax(temperatureMax);
        cw.setTemperatureMin(temperatureMin);
        cw.setTemperatureUnit(tempreatureUnit);

        //HUMIDITY
        Element humidityElement = (Element) element.getElementsByTagName("humidity").item(0);
        double humidity = Double.parseDouble(humidityElement.getAttribute("value"));
        String humidityUnit = humidityElement.getAttribute("unit");
        cw.setHumidity(humidity);
        cw.setHumidityUnit(humidityUnit);

        //PRESSURE
        Element pressureElement = (Element) element.getElementsByTagName("pressure").item(0);
        double pressure = Double.parseDouble(pressureElement.getAttribute("value"));
        String pressureUnit = pressureElement.getAttribute("unit");
        cw.setPressure(pressure);
        cw.setPressureUnit(pressureUnit);

        //WIND
        Element windElement = (Element) element.getElementsByTagName("wind").item(0);
        Element speedElement = (Element) element.getElementsByTagName("speed").item(0);
        double speed = Double.parseDouble(speedElement.getAttribute("value"));
        String speedname = speedElement.getAttribute("name");
        String gusts = windElement.getElementsByTagName("gusts").item(0).getTextContent();
        Element directionElement = (Element) windElement.getElementsByTagName("direction").item(0);
        double direction = Double.parseDouble(directionElement.getAttribute("value"));
        String directionCode = directionElement.getAttribute("code");
        String directionName = directionElement.getAttribute("name");
        Wind wind = new Wind();
        wind.setDirection(direction);
        wind.setDirectionCode(directionCode);
        wind.setDirectionName(directionName);
        wind.setGusts(gusts);
        wind.setSpeed(speed);
        wind.setSpeedName(speedname);
        cw.setWind(wind);

        //CLOUDS
        Element cloudsElement = (Element) element.getElementsByTagName("clouds").item(0);
        double clouds = Double.parseDouble(cloudsElement.getAttribute("value"));
        String cloudsName = cloudsElement.getAttribute("name");
        cw.setClouds(clouds);
        cw.setCloudsName(cloudsName);

        //VISIBILITY
        String visibility = element.getElementsByTagName("visibility").item(0).getTextContent();
        cw.setVisibility(visibility);

        //PRECIPITATION
        String precipitation = ((Element) element.getElementsByTagName("precipitation").item(0)).getAttribute("mode");
        cw.setPrecipitation(precipitation);

        //WEATHER
        Element weatherElement = (Element) element.getElementsByTagName("weather").item(0);
        int weather = Integer.parseInt(weatherElement.getAttribute("number"));
        String value = weatherElement.getAttribute("value");
        String icon = weatherElement.getAttribute("icon");
        cw.setWeather(weather);
        cw.setWeatherIcon(icon);
        cw.setWeatherValue(value);

        //LAST UPDATE
        Date lastUpdate = new Date();
        try {
            lastUpdate = sdf.parse(((Element) element.getElementsByTagName("lastupdate").item(0)).getAttribute("value").replace("T", " "));
        } catch (ParseException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        cw.setLastUpdate(lastUpdate);

        return cw;
    }

    public Document getDoc() {
        return doc;
    }
}
