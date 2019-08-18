package com.gkul.LocationFinder.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "longitude")
@JsonIgnoreProperties
public class Longitude extends GeoCoordinate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "longitude_id")
    private int id;
    @Column(name = "longitude_marker")
    private LongitudeMarker longitudeMarker;

    public Longitude() {
        super();
    }

    public Longitude(LongitudeMarker longitudeMarker,
                    int degree,
                    int arcminute) {
        super(degree, arcminute);
        this.longitudeMarker = longitudeMarker;
    }

    private enum LongitudeMarker {
        @JsonProperty("E")
        E("East"),
        @JsonProperty("W")
        W("West");

        private String marker;

        LongitudeMarker(String marker) {
            this.marker = marker;
        }
    }

    public String conciseLongitude() {
        return super.getDegree() + "°" + super.getArcminute() + "'" + longitudeMarker;
    }
}
