package smytsyk.finalProject.library.controller;

import org.apache.log4j.Logger;
import smytsyk.finalProject.library.controller.command.Command;
import smytsyk.finalProject.library.controller.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet that executes input commands
 */
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 6769021265291542103L;

    private static final Logger log = Logger.getLogger(Controller.class);

    /**
     * Implementation of GET method
     * @param req servlet request
     * @param resp servlet response
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("Get method");
        process(req, resp);
    }

    /**
     * Implementation of POST method
     * @param req servlet request
     * @param resp servlet response
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("Post method");
        process(req, resp);
    }

    /**
     * Processing of request
     * @param req servlet request
     * @param resp servlet response
     */
    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter("command");
        log.debug("Command " + commandName + " will be executed");
        Command command = CommandContainer.getCommand(commandName);
        if (command != null) command.execute(req, resp);
    }

    /**
     * Initialization of controller
     */
    @Override
    public void init() {
        log.info("Controller initialization started");
        log.info("Controller initialization finished");
    }

    /**
     * Destruction of controller
     */
    @Override
    public void destroy() {
        log.info("Controller destruction started");
        log.info("Controller destruction finished");
    }
}
