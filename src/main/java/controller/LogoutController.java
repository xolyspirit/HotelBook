package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**Контроллер выхода пользователя
 * @version 1.0
 * @author Xolyspirit */
@Controller
@WebServlet(name = "logout")
public class LogoutController {
    /**Обработка выхода пользователя. Закрытие текущей сессии*/
    @RequestMapping(value = "/logout")
    public ModelAndView getRequest(HttpServletRequest req, HttpServletResponse resp){
        ModelAndView modelAndView = new ModelAndView("redirect:getindex");
        HttpSession session = req.getSession();
        session.invalidate();
        return modelAndView;
    }
}
