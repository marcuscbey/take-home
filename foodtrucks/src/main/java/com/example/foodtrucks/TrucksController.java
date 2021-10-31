package com.example.foodtrucks;

import com.example.foodtrucks.model.FoodTruck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller()
public class TrucksController {
    @Autowired
    TrucksService trucksService;

    @PostMapping("/trucks/add")
    public ResponseEntity<String> addTruck(@RequestBody FoodTruck foodTruck) {
        //add food truck to serviceMap
        if(foodTruck == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); //Send 400
        }
        trucksService.addTruck(foodTruck);
        return ResponseEntity.status(200).body("Food truck successfully added");
    }

    @GetMapping("/trucks")
    public ResponseEntity<FoodTruck> getTruck(@RequestParam(name = "locationId") String locationId) {
        //validate locationId

        FoodTruck truck = trucksService.getTruckById(locationId);
        if(truck == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(200).body(truck);
        }
    }

    @GetMapping("/trucks/list")
    public ResponseEntity<List<FoodTruck>> getBlockOfTrucks(@RequestParam(name = "block") String block) {
        List<FoodTruck> truckList = trucksService.getTrucksByBlock(block);
        if(truckList == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(200).body(truckList);
        }
    }
}
