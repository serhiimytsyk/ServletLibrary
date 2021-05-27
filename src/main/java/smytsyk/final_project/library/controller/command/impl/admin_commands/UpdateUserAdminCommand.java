package smytsyk.final_project.library.controller.command.impl.admin_commands;

import smytsyk.final_project.library.controller.command.Command;
import smytsyk.final_project.library.controller.command.impl.UserUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command that updates information about admin
 */
public class UpdateUserAdminCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserUtil.update(req, 3);
        resp.sendRedirect("/Controller?command=return_to_admin_page");
    }
}
