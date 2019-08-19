package com.gkul.LocationFinder;

import com.gkul.LocationFinder.model.Latitude;
import com.gkul.LocationFinder.model.Location;
import com.gkul.LocationFinder.model.Longitude;
import com.gkul.LocationFinder.service.LocationFinderImpl;
import com.gkul.LocationFinder.service.LocationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LocationFinderTest {

    private LocationFinderImpl locationFinder;

    @Mock
    private LocationService locationService;

    private List<Location> polandLocationList;
    private List<Location> worldLocationList;

    private Location userLocationPoznan;
    private Location userLocationLondon;

    @Before
    public void setUp() {
        int index = 0;

        locationFinder = new LocationFinderImpl(locationService);

        Location locationSzczecin = new Location(index++,
                "Szczecin",
                new Latitude(Latitude.LatitudeMarker.N, 53, 26),
                new Longitude(Longitude.LongitudeMarker.E, 14, 32),
                0);

        Location locationWarszawa = new Location(index++,
                "Warszawa",
                new Latitude(Latitude.LatitudeMarker.N, 52, 13),
                new Longitude(Longitude.LongitudeMarker.E, 21, 0),
                0);

        Location locationGniezno = new Location(index++,
                "Gniezno",
                new Latitude(Latitude.LatitudeMarker.N, 52, 32),
                new Longitude(Longitude.LongitudeMarker.E, 17, 36),
                0);

        // Poznan
        userLocationPoznan = new Location(index++,
                "",
                new Latitude(Latitude.LatitudeMarker.N, 52, 24),
                new Longitude(Longitude.LongitudeMarker.E, 16, 56),
                0);

        polandLocationList = Arrays.asList(locationSzczecin, locationWarszawa, locationGniezno);

        Location locationNewYork = new Location(index++,
                "New York",
                new Latitude(Latitude.LatitudeMarker.N, 40, 43),
                new Longitude(Longitude.LongitudeMarker.W, 74, 0),
                0); // 5570.64 km

        Location locationTokio = new Location(index++,
                "Warszawa",
                new Latitude(Latitude.LatitudeMarker.N, 35, 41),
                new Longitude(Longitude.LongitudeMarker.E, 139, 46),
                0); // 9561.70 km

        Location locationBuenosAires = new Location(index++,
                "Buenos Aires",
                new Latitude(Latitude.LatitudeMarker.S, 34, 35),
                new Longitude(Longitude.LongitudeMarker.W, 58, 55),
                0); // 11127.39 km

        Location locationSydney = new Location(index++,
                "Sydney",
                new Latitude(Latitude.LatitudeMarker.S, 33, 52),
                new Longitude(Longitude.LongitudeMarker.E, 151, 12),
                0); // 16994.33 km

        userLocationLondon = new Location(index++,
                "London",
                new Latitude(Latitude.LatitudeMarker.N, 51, 30),
                new Longitude(Longitude.LongitudeMarker.W, 0, 7),
                0);

        worldLocationList = Arrays.asList(locationNewYork, locationTokio, locationBuenosAires, locationSydney);
    }

    @Test
    public void testFindClosestLocationPoland() {
        when(locationService.getLocations()).thenReturn(polandLocationList);

        Location closestLocation = locationFinder.findClosestLocation(userLocationPoznan);

        verify(locationService).getLocations();
        Assert.assertEquals("Gniezno", closestLocation.getLocationName());
        Assert.assertEquals(Latitude.LatitudeMarker.N, closestLocation.getLatitude().getLatitudeMarker());
        Assert.assertEquals(52, closestLocation.getLatitude().getDegree());
        Assert.assertEquals(32, closestLocation.getLatitude().getArcminute());
        Assert.assertEquals(Longitude.LongitudeMarker.E, closestLocation.getLongitude().getLongitudeMarker());
        Assert.assertEquals(17, closestLocation.getLongitude().getDegree());
        Assert.assertEquals(36, closestLocation.getLongitude().getArcminute());

    }

    @Test
    public void testFindClosestLocationWorld() {
        when(locationService.getLocations()).thenReturn(worldLocationList);

        Location closestLocation = locationFinder.findClosestLocation(userLocationLondon);

        verify(locationService).getLocations();
        Assert.assertEquals("New York", closestLocation.getLocationName());
        Assert.assertEquals(Latitude.LatitudeMarker.N, closestLocation.getLatitude().getLatitudeMarker());
        Assert.assertEquals(40, closestLocation.getLatitude().getDegree());
        Assert.assertEquals(43, closestLocation.getLatitude().getArcminute());
        Assert.assertEquals(Longitude.LongitudeMarker.W, closestLocation.getLongitude().getLongitudeMarker());
        Assert.assertEquals(74, closestLocation.getLongitude().getDegree());
        Assert.assertEquals(0, closestLocation.getLongitude().getArcminute());

    }
}
