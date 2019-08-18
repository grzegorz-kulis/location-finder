package com.gkul.LocationFinder.controller;

import com.gkul.LocationFinder.model.Location;
import com.gkul.LocationFinder.model.LocationDto;
import com.gkul.LocationFinder.model.Locations;
import com.gkul.LocationFinder.service.LocationDomainToDtoMapper;
import com.gkul.LocationFinder.service.LocationFinder;
import com.gkul.LocationFinder.service.LocationRepositoryDummy;
import com.gkul.LocationFinder.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationDomainToDtoMapper locationDomainToDtoMapper;

    @Autowired
    private LocationService locationService;

    @Autowired
    // this is just a in-memory database dummy, ideally SQL database would be used
    private LocationRepositoryDummy locationRepositoryDummy;

    @PostMapping("/save")
    public ResponseEntity saveLocations(@RequestBody Locations locations) {

        locationRepositoryDummy.addAllLocations(locations.getLocationList());
//        locationService.saveLocations(locations.getLocationList());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/getClosest")
    public ResponseEntity<LocationDto> getClosestLocation(@RequestBody Location location /* ideally we would accept LocationDto
            but for the sake of simplicity lets accept Location, keeping it simple */) {
        Location closestLocation = locationService.findClosestLocation(location);
        return ResponseEntity.ok(locationDomainToDtoMapper.map(closestLocation));
    }
}
