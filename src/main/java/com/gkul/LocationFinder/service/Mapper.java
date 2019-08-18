package com.gkul.LocationFinder.service;

public interface Mapper<T, V> {
    T map(V value);
}
