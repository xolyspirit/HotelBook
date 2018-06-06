package controller;

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
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static util.CharsetUtil.convert;
/**Контроллер авторизации пользователей
 * @version 1.0
 * @author Xolyspirit */
@Controller
@WebServlet(name = "authorization")
public class AuthorizationController {
    /**дао пользователей*/
    @Autowired
    private UsersDao usersDao;
    /**дао заказов*/
    @Autowired
    private OrdersDao ordersDao;
    /**Выполняет авторизацию пользователей
     * В зависмости от типа пользователя, отправляет на необходимую страницу*/
    @PostMapping(value = "/authorization")
    public ModelAndView authorization(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        HttpSession session = req.getSession(true);
        String loc = session.getAttribute("locale").toString();
        Locale locale = new Locale.Builder().setLanguage(loc).setRegion(loc.toUpperCase()).build();
        ResourceBundle res = ResourceBundle.getBundle("locale",locale);
        ModelAndView modelAndView = new ModelAndView();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        List<Users> users = usersDao.getuserByLogin(login);
        if (users.isEmpty()){
            modelAndView.addObject("answer", convert(res.getString("answer3")));
            modelAndView.setViewName("redirect:index");
        }
        else {
            Users currentUser = users.get(0);
            if (String.valueOf(currentUser.getPassword()).equals(password)){
                if (String.valueOf(currentUser.getRole()).equals("admin")){
                    modelAndView.addObject("listorders", ordersDao.getAllOrders());
                    modelAndView.addObject("adminTitle",convert(res.getString("adminTitle")));
                    modelAndView.addObject("showAllOrders",convert(res.getString("showAllOrders")));
                    modelAndView.addObject("adminTitle",convert(res.getString("adminTitle")));
                    modelAndView.setViewName("admin");
                }
                else {
                    session.setAttribute("name", currentUser.getName());
                    session.setAttribute("id", currentUser.getId());
                    modelAndView.setViewName("redirect:getuser");
                }
            }
            else {
                modelAndView.addObject("answer", convert(res.getString("answer4")));
                modelAndView.setViewName("redirect:getindex");
            }
        }
        modelAndView.addObject("logOut",convert(res.getString("logOut")));

        return modelAndView;
    }

}
