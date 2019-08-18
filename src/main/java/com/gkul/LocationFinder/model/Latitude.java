package com.gkul.LocationFinder.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "latitude")
@JsonIgnoreProperties
public class Latitude extends GeoCoordinate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "latitude_id")
    private int id;

    @Column(name = "latitude_marker")
    private LatitudeMarker latitudeMarker;

    public Latitude() {
        super();
    }

    public Latitude(LatitudeMarker latitudeMarker,
                    int degree,
                    int arcminute) {
        super(degree, arcminute);
        this.latitudeMarker = latitudeMarker;
    }

    private enum LatitudeMarker {
        @JsonProperty("N")
        N("North"),
        @JsonProperty("S")
        S("South");

        private String marker;

        LatitudeMarker(String marker) {
            this.marker = marker;
        }
    }

    public String conciseLatitude() {
        return super.getDegree() + "°" + super.getArcminute() + "'" + latitudeMarker;
    }
}
