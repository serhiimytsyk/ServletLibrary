package smytsyk.final_project.library.controller.command.impl.librarian_commands.go_commands;

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
 * Command that redirects librarian to page with readers
 */
public class GoToLibrarianReadersPageCommand implements Command {
    private static final int READERS_PER_PAGE = 20;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> readers = new UserService().getAllReaders();
        readers = PaginationUtil.paginateList(readers, READERS_PER_PAGE, req);
        req.getSession().setAttribute("readers", readers);
        req.getRequestDispatcher("/librarian_readers.jsp").forward(req, resp);
    }
}
