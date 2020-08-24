package com.jawahar.cityconnect.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
public class CityConnectFinderServiceTest {

    @InjectMocks
    private CityConnectFinderService cityConnectFinderService;

    //Connected cities with upper and lower case
    @ParameterizedTest
    @ValueSource(strings = {"Austin, Boston", "New York, Newark", "PHILADELPHIA, newark"})
    public void testChekIfCitiesConnected(String connectedCities){
        ReflectionTestUtils.setField(cityConnectFinderService, "textFilePath", "src/test/resources/city.txt");
        ReflectionTestUtils.setField(cityConnectFinderService, "textFileDelimeter", ",");
        String[] cities = connectedCities.split(",");
        assertTrue(cityConnectFinderService.checkIfCitiesConnected(cities[0], cities[1]));
    }

    //No connectivity cities
    @ParameterizedTest
    @ValueSource(strings = {"Austin, Richmond", "Baltimore, Plano"})
    public void testCheckNotConnectedCities(String notConnectedCities){
        ReflectionTestUtils.setField(cityConnectFinderService, "textFilePath", "src/test/resources/city.txt");
        ReflectionTestUtils.setField(cityConnectFinderService, "textFileDelimeter", ",");
        String[] cities = notConnectedCities.split(",");
        assertFalse(cityConnectFinderService.checkIfCitiesConnected(cities[0], cities[1]));
    }

    //invalid input, there are no comma separated values
    @ParameterizedTest
    @ValueSource(strings = {"Austin", "BaltimorePlano"})
    public void testInvalidInput(String notConnectedCities){
        ReflectionTestUtils.setField(cityConnectFinderService, "textFilePath", "src/test/resources/city.txt");
        ReflectionTestUtils.setField(cityConnectFinderService, "textFileDelimeter", ",");
        String[] cities = notConnectedCities.split(",");
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            cityConnectFinderService.checkIfCitiesConnected(cities[0], cities[1]);
    });
    }

}
