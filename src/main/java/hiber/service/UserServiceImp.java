package hiber.service;

import hiber.dao.CarDao;
import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;
   @Autowired
   private CarDao carDao;

   @Transactional
   @Override
   public void add(User user) {
      carDao.add(user.getCar());
      userDao.add(user);
   }

   @Transactional
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }
   @Transactional(readOnly = true)
   @Override
   public User getUserByCar(Car car){
      return userDao.getUserByCar(car);
   }

}
