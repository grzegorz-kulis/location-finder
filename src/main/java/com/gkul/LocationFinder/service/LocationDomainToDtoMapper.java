package com.gkul.LocationFinder.service;

import com.gkul.LocationFinder.model.Location;
import com.gkul.LocationFinder.model.LocationDto;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class LocationDomainToDtoMapper implements Mapper<LocationDto, Location> {


    @Override
    @Nullable
    public LocationDto map(Location value) {

        if (value == null) {
            return null;
        }

        LocationDto locationDto = new LocationDto();
        locationDto.setLocationName(value.getLocationName());
        locationDto.setLatitude(value.getLatitude().conciseLatitude());
        locationDto.setLongitude(value.getLongitude().conciseLongitude());

        return locationDto;
    }
}
