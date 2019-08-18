package com.gkul.LocationFinder.service;

import com.gkul.LocationFinder.model.Location;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class LocationRepositoryDummy {

    @Getter
    private Set<Location> locationSetRepo = new HashSet<>();

    public void addAllLocations(List<Location> locationList) {
        locationSetRepo.addAll(locationList);
    }

}
