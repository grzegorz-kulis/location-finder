package com.gkul.LocationFinder.service;

import com.gkul.LocationFinder.model.GeoCoordinate;
import com.gkul.LocationFinder.model.Latitude;
import com.gkul.LocationFinder.model.Location;
import com.gkul.LocationFinder.model.Longitude;
import org.springframework.stereotype.Service;

@Service
public class LocationFinderImpl implements LocationFinder {

    private final static double AVERAGE_RADIUS_OF_EARTH_KM = 6372.8;
    private final static int MINUTES_PER_HOUR = 60;

    private final LocationService locationService;

    public LocationFinderImpl(LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    public Location findClosestLocation(Location userLocation) {
        Location closestLocation = null;
        for (Location seedLocation : locationService.getLocations()) {
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

        double userLatitudeDecimal = userLocation.getLatitude().getLatitudeMarker().equals(Latitude.LatitudeMarker.N) ?
                decimalGeoCoordinate(userLocation.getLatitude()) : -decimalGeoCoordinate(userLocation.getLatitude());
        double userLongitudeDecimal = userLocation.getLongitude().getLongitudeMarker().equals(Longitude.LongitudeMarker.E) ?
                decimalGeoCoordinate(userLocation.getLongitude()) : -decimalGeoCoordinate(userLocation.getLongitude());

        double seedLatitudeDecimal = seedLocation.getLatitude().getLatitudeMarker().equals(Latitude.LatitudeMarker.N) ?
                decimalGeoCoordinate(seedLocation.getLatitude()) : -decimalGeoCoordinate(seedLocation.getLatitude());
        double seedLongitudeDecimal = seedLocation.getLongitude().getLongitudeMarker().equals(Longitude.LongitudeMarker.E) ?
                decimalGeoCoordinate(seedLocation.getLongitude()) : -decimalGeoCoordinate(seedLocation.getLongitude());

        /////////////////////////////////////////////////////////////////////////////////////

        double latitudeDistance = Math.toRadians(seedLatitudeDecimal - userLatitudeDecimal);
        double longitudeDistance = Math.toRadians(seedLongitudeDecimal - userLongitudeDecimal);

        double userLatitudeRadian = Math.toRadians(userLatitudeDecimal);
        double seedLatitudeRadian = Math.toRadians(seedLatitudeDecimal);

        double a = Math.pow(Math.sin(latitudeDistance / 2), 2) + Math.pow(Math.sin(longitudeDistance / 2), 2) * Math.cos(userLatitudeRadian) * Math.cos(seedLatitudeRadian);
        double c = 2 * Math.asin(Math.sqrt(a));
        double distance = AVERAGE_RADIUS_OF_EARTH_KM * c;

        seedLocation.setDistance(distance);
        return Math.abs(distance);
    }

    private double decimalGeoCoordinate(GeoCoordinate geoCoordinate) {
        return (double) geoCoordinate.getDegree() + (double) geoCoordinate.getArcminute() / (double) MINUTES_PER_HOUR;
    }
}
