package smytsyk.finalProject.library.controller;

import org.apache.log4j.Logger;
import smytsyk.finalProject.library.entitiy.User;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Set;

/**
 * Initialization of session
 */
public class SessionListener implements HttpSessionListener {
    private static final Logger log = Logger.getLogger(SessionListener.class);

    /**
     * Initializes session
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("Session initialization started");
        log.info("Session initialization finished");
    }

    /**
     * Destroys session
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("Session destruction started");
        Set<Integer> logged_user_ids = (Set<Integer>)
                se.getSession().getServletContext().getAttribute("logged_user_ids");
        User user = (User) se.getSession().getAttribute("user");
        if (user != null) {
            int id = user.getId();
            logged_user_ids.remove(id);
        }
        log.info("Session destruction finished");
    }
}
