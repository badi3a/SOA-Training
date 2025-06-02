package config;

import io.swagger.jaxrs.config.BeanConfig;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebListener
public class swaggerConfig extends HttpServlet {

    public void contextInitialized(ServletContextEvent sce) {
        BeanConfig config = new BeanConfig();
        config.setTitle("Logement et RendezVous API");
        config.setVersion("1.0.0");
        config.setBasePath("/LogementRendezVous_Etudiant_war_exploded/api");
        config.setResourcePackage("webservices");
        config.setScan(true);
    }


    public void contextDestroyed(ServletContextEvent sce) {
    }
}