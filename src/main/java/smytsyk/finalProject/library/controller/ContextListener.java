package smytsyk.finalProject.library.controller;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashSet;
import java.util.Set;

/**
 * Initialization of servlet context
 */
public class ContextListener implements ServletContextListener {
    private static final Logger log = Logger.getLogger(ContextListener.class);

    /**
     * Initializes context
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Context initialization started");
        Set<Integer> logged = new HashSet<>();
        sce.getServletContext().setAttribute("logged_user_ids", logged);
        log.info("Context initialization finished");
    }

    /**
     * Destroys context
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("Context destruction started");
        log.debug("Logged users" + sce.getServletContext().getAttribute("logged_user_ids"));
        log.info("Context destruction finished");
    }
}
