package smytsyk.final_project.library.controller.command.impl;

import org.apache.log4j.Logger;
import smytsyk.final_project.library.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command that switches UI language
 */
public class SwitchLanguageCommand implements Command {
    private static final Logger log = Logger.getLogger(SwitchLanguageCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lang = req.getParameter("lang");
        log.info("Changed language to " + lang);
        req.getSession().setAttribute("lang", lang);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
