<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link href="./index.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
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

    <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent" id="button">
        Submit
    </button>
</form>
</body>
</html>