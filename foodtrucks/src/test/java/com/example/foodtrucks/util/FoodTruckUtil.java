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

    public static FoodTruck getComplexTruck() {
        FoodTruck foodTruck = new FoodTruck();
        foodTruck.setLocationId("751253");
        foodTruck.setApplicant("Pipo's Grill");
        foodTruck.setFacilityType("Truck");
        foodTruck.setCnn("5688000");
        foodTruck.setLocationDescription("FOLSOM ST: 14TH ST to 15TH ST (1800 - 1899)");
        foodTruck.setAddress("1800 FOLSOM ST");
        foodTruck.setBlockLot("3549083");
        foodTruck.setBlock("3549");
        foodTruck.setLot("083");
        foodTruck.setPermit("16MFF-0010");
        foodTruck.setStatus("REQUESTED");
        foodTruck.setFoodItems("Tacos: Burritos: Hot Dogs: and Hamburgers");
        foodTruck.setX("6007856.719");
        foodTruck.setY("2107724.046");
        foodTruck.setLatitude("37.7678524427181");
        foodTruck.setLongitude("-122.416104892532");
        foodTruck.setSchedule("http://bsm.sfdpw.org/PermitsTracker/reports/report.aspx?title=schedule&report=rptSchedule&params=permit=16MFF-0010&ExportPDF=1&Filename=16MFF-0010_schedule.pdf");
        foodTruck.setDaysHours("");
        foodTruck.setNoiSent("");
        foodTruck.setApproved("");
        foodTruck.setReceived("2016-02-04");
        foodTruck.setPriorPermit("0");
        foodTruck.setExpirationDate("");
        foodTruck.setLocation("(37.7678524427181, -122.416104892532)");

        return foodTruck;
    }
}
