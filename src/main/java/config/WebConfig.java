package config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.*;
/**Стандартный класс регистрации контроллеров*/
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"controller"})
public class WebConfig implements WebMvcConfigurer{
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp().prefix("/views/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }
}
