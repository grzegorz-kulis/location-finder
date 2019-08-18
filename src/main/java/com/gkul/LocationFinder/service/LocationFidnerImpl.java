package com.gkul.LocationFinder.service;

import com.gkul.LocationFinder.model.GeoCoordinate;
import com.gkul.LocationFinder.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationFidnerImpl implements LocationFinder {

    private final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
    private final static double MINUTES_PER_HOUR = 60;

    @Autowired
    private LocationRepositoryDummy repositoryMock;

    @Autowired
    private LocationDomainToDtoMapper locationDomainToDtoMapper;

    @Override
    public Location findClosestLocation(Location userLocation) {

        Location closestLocation = null;

        for (Location seedLocation : repositoryMock.getLocationSetRepo()) {
            double distance = calculateDistanceInKm(userLocation, seedLocation);
            if  (closestLocation == null) {
                closestLocation = seedLocation;
            }
            else {
                if (distance < closestLocation.getDistance()) {
                    closestLocation = seedLocation;
                }
            }
        }

        return closestLocation;
    }


    private double calculateDistanceInKm(Location userLocation, Location seedLocation) {

        double userLatitudeDecimal = decimalGeoCoordinate(userLocation.getLatitude());
        double userLongitudeDecimal = decimalGeoCoordinate(userLocation.getLongitude());
        double seedLatitudeDecimal = decimalGeoCoordinate(seedLocation.getLatitude());
        double seedLongitudeDecimal = decimalGeoCoordinate(seedLocation.getLongitude());

        /////////////////////////////////////////////////////////////////////////////////////

        double latitudeDistance = Math.toRadians(userLatitudeDecimal - seedLatitudeDecimal);
        double longitudeDistance = Math.toRadians(userLongitudeDecimal - seedLongitudeDecimal);

        double userLatitudeRadian = Math.toRadians(userLatitudeDecimal);
        double seedLatitudeRadian = Math.toRadians(seedLatitudeDecimal);

        double a = Math.pow(Math.sin(latitudeDistance / 2), 2) + Math.pow(Math.sin(longitudeDistance), 2) * Math.cos(userLatitudeRadian) * Math.cos(seedLatitudeRadian);
        double c = 2 * Math.asin(Math.sqrt(a));
        return AVERAGE_RADIUS_OF_EARTH_KM * c;
    }

    private double decimalGeoCoordinate(GeoCoordinate geoCoordinate) {
        return (double) geoCoordinate.getDegree() + (double) geoCoordinate.getArcminute()/ 60.0;
    }


    public int calculateDistanceInKilometer(double userLat, double userLng,
                                            double venueLat, double venueLng) {

        double latDistance = Math.toRadians(userLat - venueLat);
        double lngDistance = Math.toRadians(userLng - venueLng);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(userLat)) * Math.cos(Math.toRadians(venueLat))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c));
    }
}
