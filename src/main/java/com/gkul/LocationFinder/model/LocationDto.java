package com.gkul.LocationFinder.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocationDto {
    private String locationName;
    private String latitude;
    private String longitude;
    private Integer distance;
}
