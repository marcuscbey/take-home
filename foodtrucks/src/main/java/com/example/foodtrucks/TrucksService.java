package com.example.foodtrucks;

import com.example.foodtrucks.model.FoodTruck;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TrucksService {
    // key: locationId, value: truck info
    Map<String, FoodTruck> locationMap;
    // key: block, value: Set of locationIds
    Map<String, Set<String>> blockMap;

    public TrucksService() throws IOException {
        // Limited by the memory size of the server
        this.locationMap = new HashMap<>();
        this.blockMap = new HashMap<>();
        loadMaps();
    }

    public void addTruck(FoodTruck truck) {

        // Add each truck to location Map
        locationMap.put(truck.getLocationId(), truck);
        if(!blockMap.containsKey(truck.getBlock())) {
            blockMap.put(truck.getBlock(), new HashSet<>());
        }
        // add each truck to block list
        blockMap.get(truck.getBlock()).add(truck.getLocationId());
    }

    public FoodTruck getTruckById(String locationId) {
        return locationMap.getOrDefault(locationId, null);
    }

    public List<FoodTruck> getTrucksByBlock(String block) {
        Set<String> blockInfo = blockMap.getOrDefault(block, new HashSet<>());
        List<FoodTruck> foodTrucks = blockInfo.stream()
                .map(id -> locationMap.getOrDefault(id, null))
                .filter(Objects::nonNull) //remove entries that aren't present in LocationMap
                .collect(Collectors.toList());

        // If there are no trucks returned then send null
        return foodTrucks.size() > 0 ? foodTrucks : null;
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
