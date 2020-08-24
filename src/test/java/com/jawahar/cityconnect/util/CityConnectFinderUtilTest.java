package com.jawahar.cityconnect.util;

import com.jawahar.cityconnect.model.CityPathPair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
public class CityConnectFinderUtilTest {

    @InjectMocks
    CityConnectFinderUtil cityConnectFinderUtil;

    @Test
    public void testCheckIfCitiesConnected(){
        List<CityPathPair> cityPairs = new ArrayList<>();
        CityPathPair cityPathPair = new CityPathPair();
        cityPathPair.setCityOne("Boston");
        cityPathPair.setCityTwo("New York");
        cityPairs.add(cityPathPair);
        CityPathPair cityPathPair1 = new CityPathPair();
        cityPathPair1.setCityOne("Philadelphia");
        cityPathPair1.setCityTwo("Newark");
        cityPairs.add(cityPathPair1);
        CityPathPair cityPathPair2 = new CityPathPair();
        cityPathPair2.setCityOne("Newark");
        cityPathPair2.setCityTwo("Boston");
        cityPairs.add(cityPathPair2);
        CityPathPair cityPathPair3 = new CityPathPair();
        cityPathPair3.setCityOne("Trenton");
        cityPathPair3.setCityTwo("Albany");
        cityPairs.add(cityPathPair3);
        CityPathPair cityPathPair4 = new CityPathPair();
        cityPathPair4.setCityOne("Austin");
        cityPathPair4.setCityTwo("Washington");
        cityPairs.add(cityPathPair4);
        CityPathPair cityPathPair5 = new CityPathPair();
        cityPathPair5.setCityOne("Richmond");
        cityPathPair5.setCityTwo("Plano");
        cityPairs.add(cityPathPair5);
        CityPathPair cityPathPair6 = new CityPathPair();
        cityPathPair6.setCityOne("Baltimore");
        cityPathPair6.setCityTwo("Orlando");
        cityPairs.add(cityPathPair6);
        CityPathPair cityPathPair7 = new CityPathPair();
        cityPathPair7.setCityOne("Orlando");
        cityPathPair7.setCityTwo("Austin");
        cityPairs.add(cityPathPair7);

        assertFalse(cityConnectFinderUtil.checkIfCitiesConnected(cityPairs, "Austin", "Boston"));
        assertTrue(cityConnectFinderUtil.checkIfCitiesConnected(cityPairs, "Newark", "New York"));
        assertFalse(cityConnectFinderUtil.checkIfCitiesConnected(cityPairs, "Baltimore", "Plano"));
    }

}
