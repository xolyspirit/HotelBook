package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

@Controller
public class LocalizationController {

    @Autowired
    private Environment env;

    @PostMapping(value = "/locale")
    public ModelAndView getRequest(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        ModelAndView modelAndView = new ModelAndView("redirect:getindex");
        HttpSession session = req.getSession();
        session.setAttribute("locale",req.getParameter("language"));
        String loc = session.getAttribute("locale").toString();
        Locale locale = new Locale.Builder().setLanguage(loc).setRegion(loc.toUpperCase()).build();
        ResourceBundle res = ResourceBundle.getBundle("locale",locale);
        return modelAndView;
    }

    public String convert(String input) throws UnsupportedEncodingException {
        return new String(input.getBytes("ISO-8859-1"),"windows-1251" );
    }
}
