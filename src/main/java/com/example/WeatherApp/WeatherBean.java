package com.example.WeatherApp;

import javax.servlet.http.Cookie;
import java.io.*;

public class WeatherBean implements java.io.Serializable {
    private String city, country;
    private String[] RawWeatherList;
    private int[] currentMinMaxTemperature;
    private String[] SunRiseSunSet;
    private int feelsLike;
    private String clouds;
    private static String filePath = "C:\\Users\\EvilM\\Github\\WeatherApp\\src\\main\\java\\com\\example\\WeatherApp\\weatherApp.ser";
    private static final long serialVersionUID = 1L;
    private Cookie cookie;

    public WeatherBean(String city, String country){
        this.city = city;
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public void setCurrentMinMaxTemperature(int[] currentMinMaxTemperature){
        this.currentMinMaxTemperature = currentMinMaxTemperature;
    }
    public void setSunRiseSunSet(String[] SunRiseSunSet){
        this.SunRiseSunSet = SunRiseSunSet;
    }
    public void setRawWeatherList(String[] setRawWeatherList){
        this.RawWeatherList = setRawWeatherList;
    }
    public void setFeelsLike(int feelsLike){
        this.feelsLike = feelsLike;
    }
    public void setClouds(String clouds){
        this.clouds = clouds;
    }
    public void setCookie(Cookie cookie){
        this.cookie = cookie;
    }


    public String getCity() {
        return city;
    }
    public String getCountry() {
        return country;
    }
    public int[] getCurrentMinMaxTemperature(){
        return currentMinMaxTemperature;
    }
    public String[] getSunRiseSunSet(){
        return SunRiseSunSet;
    }
    public String getRawWeatherList(int num){
        return RawWeatherList[num];
    }
    public int getFeelsLike(){
        return feelsLike;
    }
    public String getClouds(){
        return clouds;
    }
    public Cookie getCookie(){
        return cookie;
    }

    public static String returnCity(String StringParse){
        String deleteSwedishAAO;
        String city = "";
        for (int i = 0; i < StringParse.length(); i++){
            char c;
            if(StringParse.charAt(i) == 'ö'){
                c = 'o';
            }
            if(StringParse.charAt(i) == 'å' | StringParse.charAt(i) == 'ä'){
                c = 'a';
            }
            else{
                c = StringParse.charAt(i);
            }
            city += c;
        }
        return city;

    }

    public static void serializeObject(WeatherBean weatherBean) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(weatherBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Object deSerializeObject() {
        Object object = null;
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            object = objectInputStream.readObject();
        }catch (FileNotFoundException e){
            // New Library Will be created if file is not found.
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }





}
