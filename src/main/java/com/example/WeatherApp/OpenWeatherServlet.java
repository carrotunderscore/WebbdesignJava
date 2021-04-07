package com.example.WeatherApp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@WebServlet(name = "OpenWeatherServlet", value = "/OpenWeatherServlet")
public class OpenWeatherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        PrintWriter writer = response.getWriter();
        CSS(writer);
        writer.println("<body>");

        earlierWeathers(request, writer);
        WeatherBean weatherBean = new WeatherBean(city, country);


        WeatherBean.serializeObject(weatherBean);
        // Raw info from API
        getTheWeather.getTheWeather(weatherBean);
        getTheWeather.feelsLikeSunRiseTemperature(weatherBean);
        getTheWeather.clouds(weatherBean);

        writer.print("<h3><ul><ul class=\"current\">");
        writer.println(saveWeatherInfoData(weatherBean));
        writer.print("</ul></h3></ul>");


        Cookie cookie = new Cookie("WeatherInfo", URLEncoder.encode(saveWeatherInfoData(weatherBean), "UTF-8"));
        cookie.setMaxAge(60*60);
        response.addCookie(cookie);

        writer.println("</body>");
        writer.print("</html>");
        writer.close();
        WeatherBean.deSerializeObject();
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    public static String saveWeatherInfoData(WeatherBean weatherBean){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String city = "<li>City: " + weatherBean.getCity() + "</li>";
        String time = "\nTIME: " + dtf.format(now);
        int[] tempList = weatherBean.getCurrentMinMaxTemperature();
        String feelsLike = "\nIt feels like " + weatherBean.getFeelsLike() + "°";

        String temperature = "\nThe temperature currently is at " + tempList[0] + "°, " +
                "the low is gonna be " + tempList[1] + "° and the high is gonna be " + tempList[2] + "°";

        String clouds = "\nThere are " + weatherBean.getClouds() + ".";
        return city + "</br>" +  time + "</br>" + feelsLike + "</br>" + temperature + "</br>" + clouds;
    }
    public static void earlierWeathers(HttpServletRequest request, PrintWriter writer) throws UnsupportedEncodingException {
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie c : cookies){
                if(c.getName().equals("WeatherInfo")){
                    writer.println("<div class=\"former\"><ul><p><small><b>Previous search</b></br>");
                    writer.print(URLDecoder.decode(c.getValue(), "UTF-8"));
                    writer.println("</p></small></ul></div></br>");
                }
            }
        }
    }

    public static void CSS(PrintWriter writer){
        String cssTag = "<link rel='stylesheet' type='text/css' href='./webapp/OpenWeather.css'>";
        writer.println("<html><head>" + cssTag + "</head>"); //HTML
        writer.println("<head>");
        writer.println("<style>");
        writer.println("body{" +
                "background-image: url(\"./webapp/weatherBackground.jpg\");" +
                "}");
        writer.println(".current {" +
                "text-align: center;" +
                "color:blue;" +
                "padding: 5px;" +
                "}");
        writer.println(".former {" +
                "text-align: center;" +
                "color:blue;" +
                "padding: 5px;" +
                "}");
        /*
        writer.println("div {" +
                "  width: 300px;" +
                "  padding: 50px;" +
                "  margin: 20px;" +
                "text-align: center;" +
                "}");

         */
        writer.println("ul{" +
                "list-style-type: none;" +
                "}");
        writer.println("</style>");  // terminate style
        writer.println("</head>");
    }


}
