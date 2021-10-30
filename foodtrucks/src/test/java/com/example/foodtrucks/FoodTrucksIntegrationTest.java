package com.example.foodtrucks;

import com.example.foodtrucks.model.FoodTruck;
import com.example.foodtrucks.util.FoodTruckUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FoodTrucksIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    public void get_valid_truck() {
        ResponseEntity<FoodTruck> response =  restTemplate.getForEntity("http://localhost:" + port + "/trucks?locationId=751253", FoodTruck.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        FoodTruck outputTruck = response.getBody();
        assertThat(outputTruck.getApplicant()).isEqualTo("Pipo's Grill");
    }

    @Test
    public void insert_valid_truck() {
        FoodTruck foodTruck = FoodTruckUtil.getSimpleTruck();
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/trucks/add", foodTruck, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        //retrieve inserted truck
        ResponseEntity<FoodTruck> response1 =  restTemplate.getForEntity("http://localhost:" + port + "/trucks?locationId="
                + foodTruck.getLocationId(), FoodTruck.class);
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response1.getBody()).isEqualTo(foodTruck);
    }
}
