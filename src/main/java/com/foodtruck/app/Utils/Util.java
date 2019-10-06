package com.foodtruck.app.Utils;

import com.foodtruck.app.Model.FoodTruckInfo;

public class Util {

    public static String prettyPrint(FoodTruckInfo foodTruckInfo) {
        String applicant = String.format("%1$-30s", foodTruckInfo.getApplicant());
        String location = String.format("%1$-30s", foodTruckInfo.getLocation());
        return String.format("%s | %s", applicant, location);
    }
    public static boolean isAfterOneMinute(int preHour, int preMin, int curHour, int curMin) {
        return curHour!=preHour || curMin!=preMin;
    }
}
