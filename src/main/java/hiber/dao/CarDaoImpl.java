package hiber.dao;

import hiber.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository

public class CarDaoImpl implements CarDao{
    @Autowired
    SessionFactory sessionFactory;
    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }
}
