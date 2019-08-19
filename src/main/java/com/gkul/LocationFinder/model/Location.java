package com.gkul.LocationFinder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "locations")
@JsonIgnoreProperties
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JsonIgnore
    @Transient
    private double distance;

}
