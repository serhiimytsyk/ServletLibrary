package smytsyk.finalProject.library.controller.command.impl.adminCommands.UserCommands;

import smytsyk.finalProject.library.controller.command.Command;
import smytsyk.finalProject.library.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command that makes librarian a user
 */
public class RemoveLibrarianCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new UserService().changeRole(req, resp, 1);
    }
}