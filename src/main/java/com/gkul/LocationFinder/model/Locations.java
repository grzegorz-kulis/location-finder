package com.gkul.LocationFinder.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Locations {

    @JsonProperty("locations")
    private List<Location> locationList;
}
