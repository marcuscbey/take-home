package com.example.foodtrucks;

import com.example.foodtrucks.model.FoodTruck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller("/trucks")
public class TrucksController {
    @Autowired
    TrucksService trucksService;

    @PostMapping("/add")
    public String addTruck(FoodTruck foodTruck) {
        //add food truck to serviceMap
        if(foodTruck == null) {
            return null; //Send 400
        }
        trucksService.addTruck(foodTruck);
        return "Food truck successfully added";
    }

    @GetMapping()
    public FoodTruck getTruck(@RequestParam(name = "locationId") String locationId) {
        //validate locationId

        FoodTruck truck = trucksService.getTruckById(locationId);
        return truck;
    }

    @GetMapping("/list")
    public List<FoodTruck> getBlockOfTrucks(@RequestParam(name = "block") String block) {
        List<FoodTruck> truckList = trucksService.getTrucksByBlock(block);
        return truckList;
    }
}
