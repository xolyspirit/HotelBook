package repository;

import model.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class OrdersDao {

    @Autowired
    SessionFactory sessionFactory;

    public Orders getOrderById(Integer id){
        return  sessionFactory.getCurrentSession().get(Orders.class, id);
    }

    public List<Orders> getAllOrders(){
        Session session = sessionFactory.getCurrentSession();
        List<Orders> orders = (List<Orders>)session.createQuery("from Orders ").list();
        return  orders;
    }
    public Integer saveOrder(Orders order){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(order);
        session.flush();
        return order.getId();
    }
}
