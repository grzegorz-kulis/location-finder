package com.gkul.LocationFinder.service;

import com.gkul.LocationFinder.model.Location;

import java.util.List;

public interface LocationService {
    List<Location> getLocations();
    void saveLocation(Location location);
    void saveLocations(List<Location> locationList);
    Location getLocation(int id);

    Location findClosestLocation(Location location);
}
