package com.gkul.LocationFinder.repository;

import com.gkul.LocationFinder.model.Location;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationDAOImpl implements LocationDAO {

    private final SessionFactory sessionFactory;

    public LocationDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Location> getLocations() {
        Session currentSession = sessionFactory.getCurrentSession();
        List<Location> customers = currentSession.createQuery("from Location", Location.class).list();
        return customers;
    }

    @Override
    public void saveLocation(Location location) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(location);
    }

    @Override
    public void saveLocations(List<Location> locationList) {
        Session currentSession = sessionFactory.getCurrentSession();
        for (Location location : locationList) {
            currentSession.saveOrUpdate(location);
        }
    }

    @Override
    public Location getLocation(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Location location = currentSession.get(Location.class, id);
        return location;
    }
}
