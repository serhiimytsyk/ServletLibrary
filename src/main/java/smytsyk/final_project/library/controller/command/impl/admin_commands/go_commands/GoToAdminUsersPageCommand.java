package smytsyk.final_project.library.controller.command.impl.admin_commands.go_commands;

import smytsyk.final_project.library.controller.command.Command;
import smytsyk.final_project.library.controller.command.impl.PaginationUtil;
import smytsyk.final_project.library.entitiy.User;
import smytsyk.final_project.library.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Command that redirects admin to the page with users
 */
public class GoToAdminUsersPageCommand implements Command {
    private static final int USERS_PER_PAGE = 20;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = new UserService().getAllUsers();
        users = PaginationUtil.paginateList(users, USERS_PER_PAGE, req);
        req.getSession().setAttribute("users", users);
        req.getRequestDispatcher("/admin_users.jsp").forward(req, resp);
    }
}
