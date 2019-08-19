package com.gkul.LocationFinder.controller;

import com.gkul.LocationFinder.model.Location;
import com.gkul.LocationFinder.model.LocationDto;
import com.gkul.LocationFinder.model.Locations;
import com.gkul.LocationFinder.service.LocationDomainToDtoMapper;
import com.gkul.LocationFinder.service.LocationFinder;
import com.gkul.LocationFinder.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationDomainToDtoMapper locationDomainToDtoMapper;

    private final LocationService locationService;

    private final LocationFinder locationFinder;

    public LocationController(LocationDomainToDtoMapper locationDomainToDtoMapper,
                              LocationService locationService,
                              LocationFinder locationFinder) {
        this.locationDomainToDtoMapper = locationDomainToDtoMapper;
        this.locationService = locationService;
        this.locationFinder = locationFinder;
    }

    @GetMapping("/getLocation")
    public ResponseEntity<Location> getLocation(@RequestParam(value = "id") int id) {
        return ResponseEntity.ok(locationService.getLocation(id));
    }

    @GetMapping("/getLocations")
    public ResponseEntity<List<Location>> getLocations() {
        return ResponseEntity.ok(locationService.getLocations());
    }

    @PostMapping("/saveLocations")
    public ResponseEntity saveLocations(@RequestBody Locations locations) {
        locationService.saveLocations(locations.getLocationList());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/saveLocation")
    public ResponseEntity saveLocation(@RequestBody Location location) {
        locationService.saveLocation(location);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/getClosest")
    public ResponseEntity<LocationDto> getClosestLocation(@RequestBody Location location /* ideally we would accept LocationDto
            and use mapper but for the sake of simplicity lets accept Location, keeping it simple */) {
        Location closestLocation = locationFinder.findClosestLocation(location);
        return ResponseEntity.ok(locationDomainToDtoMapper.map(closestLocation));
    }
}
