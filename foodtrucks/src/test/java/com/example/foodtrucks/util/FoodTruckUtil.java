package com.example.foodtrucks.util;

import com.example.foodtrucks.model.FoodTruck;

public class FoodTruckUtil {

    public static FoodTruck getSimpleTruck() {
        FoodTruck foodTruck = new FoodTruck();
        foodTruck.setLocationId("1111");
        foodTruck.setBlock("1111");
        foodTruck.setApplicant("Marcus Test Truck");

        return foodTruck;
    }
}
