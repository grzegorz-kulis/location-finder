package com.gkul.LocationFinder.repository;

import com.gkul.LocationFinder.model.Location;

import java.util.List;

public interface LocationDAO {

    List<Location> getLocations();
    void saveLocation(Location location);
    void saveLocations(List<Location> locationList);
    Location getLocation(int id);

}
