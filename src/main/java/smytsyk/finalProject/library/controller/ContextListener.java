package smytsyk.finalProject.library.controller;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

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
        log.info("Context initialization finished");
    }

    /**
     * Destroys context
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("Context destruction started");
        log.info("Context destruction finished");
    }
}
