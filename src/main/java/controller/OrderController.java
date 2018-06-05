package controller;

import model.Orders;
import model.Reservations;
import model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import repository.OrdersDao;
import repository.UsersDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.*;

@Controller
@WebServlet(name = "order")
public class OrderController {

    @Autowired
    private OrdersDao ordersDao;

    @Autowired
    private UsersDao usersDao;

    @PostMapping(value = "/order")
    public ModelAndView order(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = req.getSession();
        String loc = session.getAttribute("locale").toString();
        Locale locale = new Locale.Builder().setLanguage(loc).setRegion(loc.toUpperCase()).build();
        ResourceBundle res = ResourceBundle.getBundle("locale",locale);
        int userId = (Integer) session.getAttribute("id");
        Users user = usersDao.getUserById(userId);
        Reservations reservation = new Reservations();
        reservation.setRoom(Integer.parseInt(req.getParameter("room")));
        reservation.setStartTime(Date.valueOf(req.getParameter("date")));
        reservation.setDays(Integer.parseInt(req.getParameter("days")));
        Orders order = new Orders();
        reservation.setOrder(order);
        order.setUser(user);
        order.getReservations().add(reservation);
        order.setCost(order.getCost() + Math.rint((reservation.getDays() * 5.2*100.0)/100.0));
        order.setStatus("not paid");
        user.getOrders().add(order);
        usersDao.saveUser(user);
        req.setAttribute("cost", order.getCost());
        req.setAttribute("order_id", order.getId());
        req.setAttribute("room",reservation.getRoom());
        req.setAttribute("date",reservation.getStartTime());
        req.setAttribute("days", reservation.getDays());
        req.setAttribute("sroom",convert(res.getString("room")));
        req.setAttribute("sdate",convert(res.getString("arrivalDate")));
        req.setAttribute("sdays",convert(res.getString("days")));
        req.setAttribute("scost",convert(res.getString("cost")));
        req.setAttribute("pay",convert(res.getString("pay")));
        modelAndView.setViewName("paid");
        return modelAndView;
    }
    @PostMapping(value = "/paid")
    public ModelAndView paid(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = req.getSession();
        String loc = session.getAttribute("locale").toString();
        Locale locale = new Locale.Builder().setLanguage(loc).setRegion(loc.toUpperCase()).build();
        ResourceBundle res = ResourceBundle.getBundle("locale",locale);
        Orders order = ordersDao.getOrderById(Integer.parseInt(req.getParameter("order_id")));
        order.setStatus("paid");
        ordersDao.saveOrder(order);
        req.setAttribute("answer", convert(res.getString("answer")));
        req.setAttribute("payTitle", convert(res.getString("payTitle")));
        modelAndView.setViewName("redirect:getuser");
        return modelAndView;
    }

    @PostMapping(value = "/showmyorders")
    public ModelAndView showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = req.getSession();
        String loc = session.getAttribute("locale").toString();
        Locale locale = new Locale.Builder().setLanguage(loc).setRegion(loc.toUpperCase()).build();
        ResourceBundle res = ResourceBundle.getBundle("locale",locale);
        int userId = (Integer) session.getAttribute("id");
        Set<Orders> orders = usersDao.getUserById(userId).getOrders();
        if(orders.size()>0) {
            Orders order;
            StringBuilder sb = new StringBuilder();
            Iterator<Orders> iter = orders.iterator();
            sb.append("<H3>"+convert(res.getString("orderId"))+"</H3><table>");
            while (iter.hasNext()) {
                order = iter.next();
                sb.append("<tr><th>"+convert(res.getString("orderId"))+
                        "</th><th>"+convert(res.getString("status"))+
                        "</th><th>"+convert(res.getString("cost"))+"</th></tr>");
                sb.append(order);
                Iterator<Reservations> resIter = order.getReservations().iterator();
                while(resIter.hasNext()){
                    sb.append("<tr><th>"+convert(res.getString("reservationId"))+
                            "</th><th>"+convert(res.getString("arrivalDate"))+
                            "</th><th>"+convert(res.getString("days"))+"</th></tr>");
                    sb.append(resIter.next().toString());
                }
            }
            sb.append("</table>");
            session.setAttribute("orders", sb.toString());
        }
        else {
            session.setAttribute("orders",convert(res.getString("answer2")));
        }
        modelAndView.setViewName("redirect:getuser");
        return  modelAndView;
    }

    @PostMapping(value = "/showallorders")
    public ModelAndView showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        HttpSession session = req.getSession();
        String loc = session.getAttribute("locale").toString();
        Locale locale = new Locale.Builder().setLanguage(loc).setRegion(loc.toUpperCase()).build();
        ResourceBundle res = ResourceBundle.getBundle("locale",locale);
        ModelAndView modelAndView = new ModelAndView("admin");
        List<Users> users = usersDao.getAllUsers();
        StringBuilder sb = new StringBuilder();
        Iterator<Users> userIter = users.iterator();
        sb.append("<table> <caption> "+convert(res.getString("tableName"))+"</caption>");
        while(userIter.hasNext()){
            Users user = userIter.next();
            Iterator<Orders> orderIterator = user.getOrders().iterator();
            sb.append("<tr><th>"+convert(res.getString("userName"))+"</th></tr>");
            sb.append("<tr><th>");
            sb.append(user.getName());
            sb.append("</th></tr>");
            while(orderIterator.hasNext()){
                sb.append("<tr><th>"+convert(res.getString("orderId"))+
                        "</th><th>"+convert(res.getString("status"))+
                        "</th><th>"+convert(res.getString("cost"))+"</th></tr>");
                Orders order = orderIterator.next();
                sb.append(order.toString());
                Iterator<Reservations> iter = order.getReservations().iterator();
                while(iter.hasNext()){
                    sb.append("<tr><th>"+convert(res.getString("reservationId"))+
                            "</th><th>"+convert(res.getString("arrivalDate"))+
                            "</th><th>"+convert(res.getString("days"))+"</th></tr>");
                    sb.append(iter.next().toString());
                }
            }
        }
        sb.append("</table>");
        req.setAttribute("orders", sb.toString());

        modelAndView.addObject("showAllOrders",convert(res.getString("showAllOrders")));
        modelAndView.addObject("logOut",convert(res.getString("logOut")));
        return modelAndView;
    }

    public String convert(String input) throws UnsupportedEncodingException {
        return new String(input.getBytes("ISO-8859-1"), "windows-1251");
    }

}
