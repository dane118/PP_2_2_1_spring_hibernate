package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory
                .getCurrentSession()
                .createQuery("from User as u inner join fetch u.car");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Optional<User> getUserByCar(Car car) {
       TypedQuery<User> query = sessionFactory
               .getCurrentSession()
               .createQuery("from User u join fetch u.car where u.car.model = :name_model and  u.car.series = :name_series");

        query.setParameter("name_model", car.getModel());
        query.setParameter("name_series", car.getSeries());

        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
