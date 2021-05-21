package smytsyk.finalProject.library.controller.command.impl.adminCommands;

import smytsyk.finalProject.library.controller.command.Command;
import smytsyk.finalProject.library.controller.command.impl.UpdateUserUtil;

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
        UpdateUserUtil.update(req);
        resp.sendRedirect("/Controller?command=return_to_admin_page");
    }
}
