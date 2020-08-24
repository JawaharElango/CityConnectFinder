package com.jawahar.cityconnect.service;

import com.jawahar.cityconnect.model.CityPathPair;
import com.jawahar.cityconnect.util.CityConnectFinderUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CityConnectFinderService {

    @Value("${text.file.path}")
    private String textFilePath;

    @Value("${text.file.delimeter}")
    private String textFileDelimeter;

    private static final Logger LOGGER = LogManager.getLogger(CityConnectFinderService.class);

    public boolean checkIfCitiesConnected(String origin, String destination){
        List<CityPathPair> cityPathPairList = parseTextFile();
        return new CityConnectFinderUtil().checkIfCitiesConnected(cityPathPairList, origin.toLowerCase().trim(), destination.toLowerCase().trim());
    }

    private List<CityPathPair> parseTextFile() {
        LOGGER.info("Reading text file from path {}", textFilePath);
        String line = "";
        List<CityPathPair> cityPathPairList = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(textFilePath))){
            while((line = br.readLine()) !=null){
                String[] connectedCities = line.split(textFileDelimeter);
                CityPathPair cityPathPair = new CityPathPair();
                cityPathPair.setCityOne(connectedCities[0].toLowerCase());
                cityPathPair.setCityTwo((connectedCities[1].toLowerCase()));
                cityPathPairList.add(cityPathPair);
            }
            LOGGER.info("Valid TextFile..Parsing Done");
        } catch (IOException e) {
            LOGGER.error("Something seems to be wrong with the file. Please check if the file is valid");
            e.printStackTrace();
        }
        return cityPathPairList;
    }
}
