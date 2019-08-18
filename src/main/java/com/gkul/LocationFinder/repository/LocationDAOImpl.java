package com.gkul.LocationFinder.repository;

import com.gkul.LocationFinder.model.Location;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationDAOImpl implements LocationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Location> getLocations() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Location> theQuery =
                currentSession.createQuery("from Customer order by lastName",
                        Location.class);
        List<Location> customers = theQuery.getResultList();
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
        currentSession.saveOrUpdate(locationList);
    }

    @Override
    public Location getLocation(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Location location = currentSession.get(Location.class, id);
        return location;
    }
}
