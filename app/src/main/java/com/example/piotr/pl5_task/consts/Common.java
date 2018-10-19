package com.example.piotr.pl5_task.consts;

public class Common {

    public static final String API_LINK_XML = "http://api.apixu.com/v1/forecast.xml?key=f6c739186365406f81f215626170506&q=";
    public static final String END_LINK = ",pt&days=7";
    public static final String API_LINK_JSON = "http://api.apixu.com/v1/";
    public static final String API_KEY = "f6c739186365406f81f215626170506";

    public static String apiRequest(String city) {
        return API_LINK_JSON + city + END_LINK;
    }


}
