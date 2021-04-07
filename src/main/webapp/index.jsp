<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link href="./index.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<style type="text/css">

</style>
<body>

<br/>
<img src="./Images/weathers.jpg" alt="weatherRainbow" width="1066" height="600" class="rainbow">
<form action="OpenWeatherServlet">
    <label for="city">City:</label><br>
    <input type="text" id="city" name="city"><br>

    <label for="country">Country</label><br>
    <input type="text" id="country" name="country"><br>

    <input type="submit" id="button" name="button" class="button">
</form>
</body>
</html>