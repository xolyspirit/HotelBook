package repository;

import model.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**Репозиторий пользователей {@link Users}
 * @version 1.0
 * @author Xolyspirit */
@Repository
@Transactional
public class UsersDao {
    /**вабрика сессий*/
    @Autowired
    private SessionFactory sessionFactory;
    /**Принимает логин пользователя @param login.
     * возвращает список пользователей @return userList с таким логином*/
    public List<Users> getuserByLogin(String login){
        Session session = sessionFactory.getCurrentSession();
        List<Users> usersList = (List<Users>) session.createQuery(
                "from Users where login = '" + login + "'").list();
        return usersList;
    }
    /**Принимает id пользователя @param id.
     * возвращает пользователя с таким id*/
    public Users getUserById(Integer id){
        return sessionFactory.getCurrentSession().get(Users.class, id);
    }
    /**Принимает экземпляр пользователя @param user.
     * возвращает id пользователя в базе*/
    public Integer saveUser(Users user){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
        session.flush();
        return user.getId();
    }
    /**Выдает список всех пользователей @return users*/
    public List<Users> getAllUsers(){
        Session session = sessionFactory.getCurrentSession();
        List<Users> usersList = (List<Users>) session.createQuery(
                "from Users where role = '" + "user" + "'").list();
        return usersList;
    }
}
