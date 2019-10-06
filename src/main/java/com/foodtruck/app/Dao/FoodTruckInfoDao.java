package com.foodtruck.app.Dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FoodTruckInfoDao {
    private final static String basicUrl = "https://data.sfgov.org/resource/bbb8-hzi6.json";

    public String getRawData() {
        StringBuilder result = new StringBuilder();
        try {
            SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE,HH,mm");
            String[] date = simpleDateformat.format(new Date()).split(",");
            String dayOfWeek = date[0];
            String time = date[1] + ":" + date[2];
            String query = "?$query=SELECT DISTINCT applicant,location WHERE dayofweekstr='" +
                    dayOfWeek + "' AND start24<='" + time + "' AND end24>'" + time +
                    "' ORDER BY applicant ASC";
            String fullUrl = basicUrl + query;
            fullUrl = fullUrl.replace(" ","%20");
            URL url = new URL(fullUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return result.toString();
    }
}
