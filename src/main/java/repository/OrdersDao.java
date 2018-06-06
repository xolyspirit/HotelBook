package repository;

import model.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**Репозиторий заказов {@link Orders}
 * @version 1.0
 * @author Xolyspirit */
@Repository
@Transactional
public class OrdersDao {
    /**Фабрика сессий*/
    @Autowired
    SessionFactory sessionFactory;
    /**Примает id заказа @param id
     * возвращает найденный заказ*/
    public Orders getOrderById(Integer id){
        return  sessionFactory.getCurrentSession().get(Orders.class, id);
    }
    /**Выдает список всех заказов @return orders*/
    public List<Orders> getAllOrders(){
        Session session = sessionFactory.getCurrentSession();
        List<Orders> orders = (List<Orders>)session.createQuery("from Orders ").list();
        return  orders;
    }
    /**Сохраняет переданный заказ @param order
     * возвращает его id @return order.getId()*/
    public Integer saveOrder(Orders order){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(order);
        session.flush();
        return order.getId();
    }
}
