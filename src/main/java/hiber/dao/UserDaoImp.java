package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        if (user.getCar() == null) {
            sessionFactory.getCurrentSession().save(user);
        } else {
            sessionFactory.getCurrentSession().save(user.getCar());
            sessionFactory.getCurrentSession().save(user);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserByCar(Car car) {

        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("select u from User u "
                                                                                + "where u.car.model = :name_model "
                                                                                + "and u.car.series = :name_series");
        query.setParameter("name_model", car.getModel());
        query.setParameter("name_series", car.getSeries());

        User singleResult;
        try {
            singleResult = query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

        return singleResult;
    }
}
