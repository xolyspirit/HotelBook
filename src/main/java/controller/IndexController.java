package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

import static util.CharsetUtil.convert;

/**Контроллер главной страницы
 * @version 1.0
 * @author Xolyspirit */
@Controller
public class IndexController {
    /**Обработка стартового запроса*/
    @GetMapping(value = "/")
    public ModelAndView getRequest(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        ModelAndView modelAndView = new ModelAndView("index");
        HttpSession session = req.getSession();
        if (session.getAttribute("locale")==null){
            session.setAttribute("locale","en");
        }
        String loc = session.getAttribute("locale").toString();
        Locale locale = new Locale.Builder().setLanguage(loc).setRegion(loc.toUpperCase()).build();
        ResourceBundle res = ResourceBundle.getBundle("locale",locale);
        req.setAttribute("submit",convert(res.getString("submit")));
        req.setAttribute("login",convert(res.getString("login")));
        req.setAttribute("password",convert(res.getString("password")));
        req.setAttribute("logIn",convert(res.getString("logIn")));
        req.setAttribute("indexTitle",convert(res.getString("indexTitle")));
        return modelAndView;
    }
    /**Возврат на стартовую страницу*/
    @GetMapping(value = "/getindex")
    public ModelAndView getIndex(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        ModelAndView modelAndView = new ModelAndView("index");
        HttpSession session = req.getSession();
        if (session.getAttribute("locale")==null)
            session.setAttribute("locale", "en");
        String loc = session.getAttribute("locale").toString();
        Locale locale = new Locale.Builder().setLanguage(loc).setRegion(loc.toUpperCase()).build();
        ResourceBundle res = ResourceBundle.getBundle("locale",locale);
        req.setAttribute("submit",convert(res.getString("submit")));
        req.setAttribute("login",convert(res.getString("login")));
        req.setAttribute("password",convert(res.getString("password")));
        req.setAttribute("logIn",convert(res.getString("logIn")));
        req.setAttribute("indexTitle",convert(res.getString("indexTitle")));
        return modelAndView;
    }
    /**Возврат на страницу пользователя*/
    @GetMapping(value = "/getuser")
    public ModelAndView getUser(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        ModelAndView modelAndView = new ModelAndView("user");
        HttpSession session = req.getSession();
        if (session.getAttribute("locale")==null){
            session.setAttribute("locale","en");
        }
        String loc = session.getAttribute("locale").toString();
        Locale locale = new Locale.Builder().setLanguage(loc).setRegion(loc.toUpperCase()).build();
        ResourceBundle res = ResourceBundle.getBundle("locale",locale);
        modelAndView.addObject("userTitle",convert(res.getString("userTitle")));
        modelAndView.addObject("userTitle",convert(res.getString("userTitle")));
        modelAndView.addObject("room",convert(res.getString("room")));
        modelAndView.addObject("arrivalDate",convert(res.getString("arrivalDate")));
        modelAndView.addObject("days",convert(res.getString("days")));
        modelAndView.addObject("order",convert(res.getString("order")));
        modelAndView.addObject("showAllMyOrders",convert(res.getString("showAllMyOrders")));
        modelAndView.addObject("show",convert(res.getString("show")));
        modelAndView.addObject("myOrders",convert(res.getString("myOrders")));
        modelAndView.addObject("logOut",convert(res.getString("logOut")));
        modelAndView.addObject("greeting",
                convert(res.getString("greeting")
                        +session.getAttribute("name")));
        return modelAndView;
    }
}
