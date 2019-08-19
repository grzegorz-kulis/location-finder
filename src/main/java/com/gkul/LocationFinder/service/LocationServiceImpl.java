package com.gkul.LocationFinder.service;

import com.gkul.LocationFinder.model.Location;
import com.gkul.LocationFinder.repository.LocationDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationDAO locationDAO;

    public LocationServiceImpl(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

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
    @Transactional
    public void saveLocations(List<Location> locationList) {
        locationDAO.saveLocations(locationList);
    }

    @Override
    @Transactional
    public Location getLocation(int id) {
        return locationDAO.getLocation(id);
    }

}
