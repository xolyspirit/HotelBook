package repository;

import model.Reservations;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ReservationsDao {

    @Autowired
    SessionFactory sessionFactory;

    public Reservations getReservation(Integer id){
       return sessionFactory.getCurrentSession().get(Reservations.class, id);
    }
    public Integer saveReservation( Reservations reservation){
        Session session = sessionFactory.getCurrentSession();
        session.save(reservation);
        session.flush();
        return reservation.getId();    }



}
