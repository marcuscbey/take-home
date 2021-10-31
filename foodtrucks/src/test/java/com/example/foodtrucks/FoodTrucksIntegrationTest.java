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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FoodTrucksIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void get_valid_truck() {
        FoodTruck foodTruck = FoodTruckUtil.getComplexTruck();
        ResponseEntity<FoodTruck> response =  restTemplate.getForEntity("http://localhost:" + port + "/trucks?locationId=" +
                foodTruck.getLocationId(), FoodTruck.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        FoodTruck outputTruck = response.getBody();
        assertThat(outputTruck).isEqualTo(foodTruck);
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

    @Test
    public void get_valid_block() {
        String block = "3754";
        ResponseEntity<List> response =  restTemplate.getForEntity("http://localhost:" + port + "/trucks/list?block=" +
                block, List.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<FoodTruck> outputTruckList = response.getBody();
        assertThat(outputTruckList.size()).isEqualTo(3);
    }

    @Test
    public void add_to_block() {
        String block = "5343";
        ResponseEntity<List> response =  restTemplate.getForEntity("http://localhost:" + port + "/trucks/list?block=" +
                block, List.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<FoodTruck> outputTruckList = response.getBody();
        assertThat(outputTruckList.size()).isEqualTo(1);

        //Add new truck to existing block
        FoodTruck simpleTruck = FoodTruckUtil.getSimpleTruck();
        simpleTruck.setBlock(block);
        ResponseEntity<String> response1 = restTemplate.postForEntity("http://localhost:" + port + "/trucks/add", simpleTruck, String.class);
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<List> response2 =  restTemplate.getForEntity("http://localhost:" + port + "/trucks/list?block=" +
                block, List.class);

        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<FoodTruck> outputTruckList1 = response2.getBody();
        assertThat(outputTruckList1.size()).isEqualTo(2);
    }

    @Test
    public void get_invalid_block() {
        String block = "fake";
        ResponseEntity<List> response =  restTemplate.getForEntity("http://localhost:" + port + "/trucks/list?block=" +
                block, List.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
