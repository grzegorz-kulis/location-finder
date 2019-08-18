package com.gkul.LocationFinder.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Data
@Entity
@Table(name = "locations")
@JsonIgnoreProperties
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "location_id")
    private int id;
    @Nullable
    @Column(name = "location_name")
    private String locationName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "latitude_id")
    private Latitude latitude;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "longitude_id")
    private Longitude longitude;

    @Nullable
    private double distance;

}
