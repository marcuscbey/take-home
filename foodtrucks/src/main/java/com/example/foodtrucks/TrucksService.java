package com.example.foodtrucks;

import com.example.foodtrucks.model.FoodTruck;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TrucksService {
    // key: locationId, value: truck info
    Map<String, FoodTruck> locationMap;
    // key: block, value: list of locationIds
    Map<String, List<String>> blockMap;

    public TrucksService() throws IOException {
        this.locationMap = new HashMap<>();
        this.blockMap = new HashMap<>();
        loadMaps();
    }

    public void addTruck(FoodTruck truck) {
        //TODO: Validate truck object

        // Add each truck to location Map
        locationMap.put(truck.getLocationId(), truck);
        if(!blockMap.containsKey(truck.getBlock())) {
            blockMap.put(truck.getBlock(), new ArrayList<>());
        }
        // add each truck to block list
        blockMap.get(truck.getBlock()).add(truck.getLocationId());
    }

    public FoodTruck getTruckById(String locationId) {
        return locationMap.get(locationId);
    }

    public List<FoodTruck> getTrucksByBlock(String block) {
        List<String> blockInfo = blockMap.get(block);
        List<FoodTruck> foodTrucks = blockInfo.stream()
                .map(id -> locationMap.get(id)) //TODO: what if not in locationMap
                .collect(Collectors.toList());

        return foodTrucks;
    }

    public void loadMaps() throws IOException {
        CsvMapper mapper = new CsvMapper();
        //Create schema from POJO. add withHeader to skip Header row
        CsvSchema truckSchema = mapper.schemaFor(FoodTruck.class).withHeader();

        // TODO: Parameterize file path
        MappingIterator<FoodTruck> iter = mapper.readerFor(FoodTruck.class)
                .with(truckSchema)
                .readValues(new File("src/main/resources/Mobile_Food_Facility_Permit.csv"));

        while(iter.hasNextValue()) {
            FoodTruck truck = iter.nextValue();
            addTruck(truck);
        }
    }
}
