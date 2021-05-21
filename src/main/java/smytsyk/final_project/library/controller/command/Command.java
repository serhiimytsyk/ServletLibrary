package smytsyk.final_project.library.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    /**
     * Makes something with request and response
     */
    void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
