package repository;

import model.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UsersDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Users> getuserByLogin(String login){
        Session session = sessionFactory.getCurrentSession();
        List<Users> usersList = (List<Users>) session.createQuery(
                "from Users where login = '" + login + "'").list();
        return usersList;
    }

    public Users getUserById(Integer id){
        return sessionFactory.getCurrentSession().get(Users.class, id);
    }

    public Integer saveUser(Users user){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
        session.flush();
        return user.getId();
    }
    public List<Users> getAllUsers(){
        Session session = sessionFactory.getCurrentSession();
        List<Users> usersList = (List<Users>) session.createQuery(
                "from Users where role = '" + "user" + "'").list();
        return usersList;
    }
}
