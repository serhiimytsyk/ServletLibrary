package smytsyk.final_project.library.controller.command.impl.commands_without_control;

import org.apache.log4j.Logger;
import smytsyk.final_project.library.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command that is not valid and redirects user to error page
 */
public class InvalidCommand implements Command {
    private static final Logger log = Logger.getLogger(InvalidCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.error("Invalid command");
        req.getRequestDispatcher("/error_page.jsp").forward(req, resp);
    }
}
