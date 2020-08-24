package com.jawahar.cityconnect.util;

import com.jawahar.cityconnect.model.CityPathPair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CityConnectFinderUtil {

    private static final Logger LOGGER = LogManager.getLogger(CityConnectFinderUtil.class);

    private Map<String, LinkedHashSet<String>> map = new HashMap();
    private static String  START;
    private static String  END;
    private static boolean flag;
    private static boolean connected;

    public void addPath(String city1, String city2)
    {
        LinkedHashSet<String> adjacentCity = map.get(city1);
        if (adjacentCity == null)
        {
            adjacentCity = new LinkedHashSet();
            map.put(city1, adjacentCity);
        }
        adjacentCity.add(city2);
    }

    public void addTwoWayVertex(String city1, String city2)
    {
        addPath(city1, city2);
        addPath(city2, city1);
    }

    public LinkedList<String> getAdjacentCities(String last)
    {
        LinkedHashSet<String> adjacentCities = map.get(last);
        if (adjacentCities == null)
        {
            return new LinkedList();
        }
        return new LinkedList<String>(adjacentCities);
    }

    public  boolean checkIfCitiesConnected(List<CityPathPair> cityPathPairList, String inputOrigin, String inputDestination)
    {
        CityConnectFinderUtil graph = new CityConnectFinderUtil();
        CityConnectFinderUtil.connected = false;
        cityPathPairList.forEach(cityPathPair -> graph.addTwoWayVertex(cityPathPair.getCityOne(), cityPathPair.getCityTwo()));
        LinkedList<String> visitedCities = new LinkedList();
        START = inputOrigin;
        END = inputDestination;

        visitedCities.add(START);
        new CityConnectFinderUtil().breadthFirst(graph, visitedCities);
        return connected;
    }

    private void breadthFirst(CityConnectFinderUtil graph,
                              LinkedList<String> visitedCities)
    {
        LinkedList<String> cities = graph.getAdjacentCities(visitedCities.getLast());

        for (String city : cities)
        {
            if (visitedCities.contains(city))
            {
                continue;
            }
            if (city.equals(END))
            {
                visitedCities.add(city);
                getPathBetweenTwoCities(visitedCities);
                flag = true;
                visitedCities.removeLast();
                break;
            }
        }

        for (String city : cities)
        {
            if (visitedCities.contains(city) || city.equals(END))
            {
                continue;
            }
            visitedCities.addLast(city);
            breadthFirst(graph, visitedCities);
            visitedCities.removeLast();
        }

        if (flag == false)
        {
            LOGGER.info("No Direct path Exists between {}  and {}",  START, END);
            flag = true;
        }
    }

    private void getPathBetweenTwoCities(LinkedList<String> visitedCities)
    {
        visitedCities.forEach(city -> LOGGER.info(city));
        connected = true;
    }
}
