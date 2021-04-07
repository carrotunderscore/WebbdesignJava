package com.example.WeatherApp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class getTheWeather {


    public static String getTheWeather(WeatherBean weatherBean)  throws IOException {
        String city = weatherBean.getCity();
        String URLtoSend = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=9f7291b8263688800bf1dcb7ba15e06d&mode=xml";
        URL lineAPIurl = new URL(URLtoSend);

        HttpURLConnection lineConnection = (HttpURLConnection) lineAPIurl.openConnection();

        lineConnection.setDoInput(true);
        lineConnection.setDoOutput(true);

        lineConnection.setRequestMethod("GET");

        BufferedReader input = new BufferedReader(new InputStreamReader(lineConnection.getInputStream()));

        String inputLine;
        String APIresponse = null;

        while((inputLine=input.readLine()) != null){
            APIresponse += inputLine;
        }

        assert APIresponse != null;
        weatherBean.setRawWeatherList(rawFormatWeatherList(APIresponse));
        return APIresponse;
    }

    public static void printRawFormatWeatherList(String weatherInfo[], PrintWriter writer){
        for(String line : weatherInfo){
            writer.println(line);
        }
    }
    public static String[] rawFormatWeatherList(String rawWeatherInfo){
        String splitByLessThen[] = rawWeatherInfo.split("<");
        return splitByLessThen;
    }
    public static double[] getTemperatureInKelvin(WeatherBean weatherBean){
        String RawWeatherList = weatherBean.getRawWeatherList(13);
        String temperature[] = RawWeatherList.split("\"" );
        double currentTemperature = Double.parseDouble(temperature[1]);
        double minTemperature = Double.parseDouble(temperature[3]);
        double maxTemperature = Double.parseDouble(temperature[5]);
        return new double[]{currentTemperature, minTemperature, maxTemperature};
    }
    // Get current=0, minimum=1 or maximum=2 based on input number
    public static int convertKelvinToCelsius(double[] kelvin, int currentMinMax){
        return (int) kelvin[currentMinMax] - 273;
    }
    public static int convertKelvinToCelsiusFeelsLike(String feelsLikeList){
        String[] feelsLikeSplit = feelsLikeList.split("\"");
        String feelsLikeKelvinValue = feelsLikeSplit[1];
        double feelsLikeDouble = Double.parseDouble(feelsLikeKelvinValue);
        return (int) feelsLikeDouble - 273;
    }
    public static String ParseSunRiseSunSet(String rawInfoList, int riseOrSet){
        String[] setRiseSplit = rawInfoList.split("\"");
        //SunRise = 1, SunSet = 3
        return setRiseSplit[riseOrSet];
    }

    //One function for calling the three functions below. Just because it looks more tidy.
    public static void feelsLikeSunRiseTemperature(WeatherBean weatherBean){
        feelsLike(weatherBean);
        sunRiseSunSet(weatherBean);
        temperature(weatherBean);
    }
    public static void feelsLike(WeatherBean weatherBean){
        String feelsLikeValue = weatherBean.getRawWeatherList(15);
        getTheWeather.getTemperatureInKelvin(weatherBean);
        weatherBean.setFeelsLike(getTheWeather.convertKelvinToCelsiusFeelsLike(feelsLikeValue));
    }
    public static void sunRiseSunSet(WeatherBean weatherBean){
        String rawSunRiseSunSetList = weatherBean.getRawWeatherList(10);
        String[] sunSetSunRise = { getTheWeather.ParseSunRiseSunSet(rawSunRiseSunSetList, 1),
                getTheWeather.ParseSunRiseSunSet(rawSunRiseSunSetList, 3) };
        String[] dateTimeSplit = Arrays.toString(sunSetSunRise).split("T");
        weatherBean.setSunRiseSunSet(dateTimeSplit);
    }
    public static void temperature(WeatherBean weatherBean){
        double[] kelvinTemp = getTheWeather.getTemperatureInKelvin(weatherBean);
        int[] current = { getTheWeather.convertKelvinToCelsius(kelvinTemp, 0),
                getTheWeather.convertKelvinToCelsius(kelvinTemp, 1),
                getTheWeather.convertKelvinToCelsius(kelvinTemp, 2) };
        weatherBean.setCurrentMinMaxTemperature(current);
    }
    public static void clouds(WeatherBean weatherbean){
        String rawCloudString = weatherbean.getRawWeatherList(29);
        String[] splitRawCloudString = rawCloudString.split("\"");
        String cloudValue = splitRawCloudString[3];
        weatherbean.setClouds(cloudValue);     
    }
    public static void city(WeatherBean weatherbean){
        String rawCloudString = weatherbean.getRawWeatherList(3);
        String[] splitRawCloudString = rawCloudString.split("\"");
        String cityValue = splitRawCloudString[3];
        weatherbean.setCity(cityValue);
    }
}







