# Food Truck Service

##Basic Usage
To run the application you must first have maven installed and run the following command:

```mvn clean package```

Then you can start the API via this command:

```java -jar foodtrucks/target/foodtrucks-1.0-SNAPSHOT.jar```

API can be accessed via http://localhost:8080

## API Guidelines

There are three main functions of the Food Trucks API
- /trucks/add - POST - This service will add a new Food Truck to the data set
- /trucks - GET - This service will return a food truck based on the locationId passed in
- /trucks/list - GET - This service will return a list of food trucks associated with the passed in block
