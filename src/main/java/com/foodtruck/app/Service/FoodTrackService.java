package com.foodtruck.app.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodtruck.app.Dao.FoodTruckInfoDao;
import com.foodtruck.app.Model.FoodTruckInfo;

import java.util.ArrayList;
import java.util.List;

public class FoodTrackService {

    private static FoodTruckInfoDao foodTruckInfoDao;
    private static ObjectMapper objectMapper = new ObjectMapper();

    public FoodTrackService() {
        foodTruckInfoDao = new FoodTruckInfoDao();
    }

    public List<FoodTruckInfo> getFoodTruckData() {
        String jsonString = foodTruckInfoDao.getRawData();
        List<FoodTruckInfo> res = parseData(jsonString);
        return res;
    }

    private static List<FoodTruckInfo> parseData(String jsonString) {
        List<FoodTruckInfo> res = new ArrayList<>();
        try {
            res = objectMapper.readValue(jsonString, objectMapper.getTypeFactory().constructCollectionType(List.class, FoodTruckInfo.class));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
    }
}
