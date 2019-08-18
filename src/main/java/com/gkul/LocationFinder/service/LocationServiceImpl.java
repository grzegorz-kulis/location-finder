package com.gkul.LocationFinder.service;

import com.gkul.LocationFinder.model.Location;
import com.gkul.LocationFinder.repository.LocationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationDAO locationDAO;

    @Autowired
    private LocationFinder locationFinder;

    @Override
    @Transactional
    public List<Location> getLocations() {
        return locationDAO.getLocations();
    }

    @Override
    @Transactional
    public void saveLocation(Location location) {
        locationDAO.saveLocation(location);
    }

    @Override
    public void saveLocations(List<Location> locationList) {
        locationDAO.saveLocations(locationList);
    }

    @Override
    @Transactional
    public Location getLocation(int id) {
        return locationDAO.getLocation(id);
    }

    @Override
    public Location findClosestLocation(Location location) {
        return locationFinder.findClosestLocation(location);
    }
}
