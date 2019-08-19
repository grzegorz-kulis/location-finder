package com.gkul.LocationFinder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class GeoCoordinate {

    @Column(name = "degree")
    private int degree;
    @Column(name = "arcminute")
    private int arcminute;
}
