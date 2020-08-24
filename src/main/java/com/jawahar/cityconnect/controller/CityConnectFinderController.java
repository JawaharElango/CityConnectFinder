package com.jawahar.cityconnect.controller;

import com.jawahar.cityconnect.service.CityConnectFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class CityConnectFinderController {

    private static final Logger LOGGER = LogManager.getLogger(CityConnectFinderController.class);
    private CityConnectFinderService cityConnectFinderService;

    @Autowired
    public CityConnectFinderController(CityConnectFinderService cityConnectFinderService) {
        this.cityConnectFinderService = cityConnectFinderService;
    }

    @GetMapping("/connected")
    public String checkIfCitiesConnected(@RequestParam String origin, @RequestParam String destination) {
        LOGGER.info("Request received..");
        boolean areCitiesConnected = cityConnectFinderService.checkIfCitiesConnected(origin, destination);
        if (areCitiesConnected) {
            return "yes";
        }
        return "no";
    }
}
