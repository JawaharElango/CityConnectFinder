# CityConnectFinder
This Spring Boot microservice finds out if two cities are connected. List of roads is available in city.txt file and is located under src/main/resources. It has the below content:
   
   
    Boston, New York
    Philadelphia,Newark
    Newark,Boston
    Trenton,Albany
    Washington,Philadelphia
    Austin,Washington
    Richmond,Plano
    Baltimore,Orlando
    Orlando,Austin
    
 The above city pairs indicates that there is a road between those cities. This Spring Boot microservice exposes an endpoint:
   
   http://localhost:8080/connected?origin=city1&destination=city2
   
  This endpoint will respond with 'yes' if city1 is connected to city2, 'no' if city1 is not connected with city2. Any unexpected input will result in a 'no' response.
  
  Examples:
  
  http://localhost:8080/connected?origin=Boston&destination=Philadelphia
  
  should return 'yes' since these cities are connected as 'Boston -> Newark -> Philadelphia'
  
  http://localhost:8080/connected?origin=Richmond&destination=Orlando
  
  should return 'no' as there is no connectivity between these two cities

#### Prerequisites:
  Java 8 
  
  Maven 3.6
  
  Postman or any REST client for Testing.
    
#### Local Setup

   git clone https://github.com/jawaharsp/CityConnectFinder
   
   Navigate to the location in terminal and start the embedded tomcat server using mvn spring-boot:run

### Testing

   The postman collection is created and uploaded under postman-collection folder in this repository. This collection can be imported and used for testing.

   This microservice is also swagger enbaled. Please use this link to test the microservice when your service is up and running.
   
   http://localhost:8080/swagger-ui.html#!/city-connect-finder-controller/checkIfCitiesConnectedUsingGET



