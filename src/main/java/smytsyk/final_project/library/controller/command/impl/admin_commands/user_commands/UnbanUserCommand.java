package smytsyk.final_project.library.controller.command.impl.admin_commands.user_commands;

import smytsyk.final_project.library.controller.command.Command;
import smytsyk.final_project.library.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command that unbans banned user
 */
public class UnbanUserCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new UserService().changeRole(req, resp, 1);
    }
}
