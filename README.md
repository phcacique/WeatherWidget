# WeatherWidget
<h3>About</h3>
<p>This is a project made for teaching about Web Services and custom Taglibs in java.
We're using the OpenWeatherAPI for lookup the current weather for a location. It uses XML for represent the current weather, and we use JAXP for parsing. Then, we made a custom taglib to include it on JSP files.</p>

<h3>Using</h3>
<p>To use the taglib, include the prefix ont he JSP:</p>
<p><code><%@taglib prefix="weather" uri="/WEB-INF/tlds/weather.tld" %></code></p>
<p>Then use the taglib like this:</p>
<p><code>&lt;weather:Current city="SaoPaulo" appid=""/&gt;</code></p>
<p>The attributes are:</p>
<ul>
  <li>appid (reuired) - The app id (get it on <a href="http://openweathermap.org/">http://openweathermap.org/</a>) 
  <li>city (required) - city name (use ISO 3166 country codes)</li>
  <li>unit - metric for temperature (optional):
      <ul>
        <li>kelvin</li>
        <li>metric (Celsius)</li>
        <li>imperial (Fahrenheit)</li>
      </ul>
  </li>
</ul>
