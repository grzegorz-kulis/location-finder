package com.gkul.LocationFinder.service;

import com.gkul.LocationFinder.model.Location;

public interface LocationFinder {
    Location findClosestLocation(Location location);
}
