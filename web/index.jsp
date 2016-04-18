<%-- 
    Document   : index
    Created on : Apr 18, 2016, 2:26:23 PM
    Author     : cacique
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="weather" uri="/WEB-INF/tlds/weather.tld" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Weather Test</title>
        <link href="css/weather.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <!-- Fill with your APP ID from openweatherapi.org-->
        <weather:Current city="SaoPaulo" appid=""/>
        <weather:Current city="London" unit="imperial" appid=""/>
        <weather:Current city="Paris" unit="kelvin" appid=""/>
    </body>
</html>
