package smytsyk.finalProject.library.controller.command.impl.readerCommands.GoCommands;

import smytsyk.finalProject.library.controller.command.Command;
import smytsyk.finalProject.library.entitiy.Book;
import smytsyk.finalProject.library.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Command that redirects user to book catalog page
 */
public class GoToCatalogPageCommand implements Command {
    private static final int BOOKS_PER_PAGE = 5;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name") == null ? "" : req.getParameter("name");
        String author = req.getParameter("author") == null ? "" : req.getParameter("author");
        String comparatorName = req.getParameter("comparator") == null ? "" : req.getParameter("comparator");
        Comparator<Book> comparator = getComparatorByName(comparatorName);
        List<Book> books = new BookService().getFreeBooks().stream().filter(b -> b.getName().startsWith(name))
                .filter(b -> b.getAuthor().startsWith(author)).sorted(comparator).collect(Collectors.toList());

        String pageStr = req.getParameter("page");
        if (pageStr == null || pageStr.isEmpty()) pageStr = "0";
        int page = Integer.parseInt(pageStr);

        books = books.stream().skip(page * BOOKS_PER_PAGE).limit(BOOKS_PER_PAGE).collect(Collectors.toList());

        req.getSession().setAttribute("pageNum", page);
        req.getSession().setAttribute("name", name);
        req.getSession().setAttribute("author", author);
        req.getSession().setAttribute("comparator", comparatorName);
        req.getSession().setAttribute("books", books);
        req.getSession().setAttribute("formatter", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        req.getRequestDispatcher("/catalog.jsp").forward(req, resp);

    }

    private static final Comparator<Book> ID_COMPARATOR = Comparator.comparingInt(Book::getId);
    private static final Comparator<Book> NAME_COMPARATOR = Comparator.comparing(Book::getName);
    private static final Comparator<Book> AUTHOR_COMPARATOR = Comparator.comparing(Book::getAuthor);
    private static final Comparator<Book> PUBLISHER_COMPARATOR = Comparator.comparing(Book::getPublisher);
    private static final Comparator<Book> DATE_COMPARATOR = Comparator.comparing(Book::getPublicationDate);

    /**
     * Gets book comparator ny name
     * Returns comparator by name if name is "name"
     * Returns comparator by author if name is "author"
     * Returns comparator by publisher if name is "publisher"
     * Returns comparator by publication date if name is "date"
     * Returns comparator by id in other cases
     */
    public Comparator<Book> getComparatorByName(String name) {
        switch (name) {
            case "name": return NAME_COMPARATOR;
            case "author": return AUTHOR_COMPARATOR;
            case "publisher": return PUBLISHER_COMPARATOR;
            case "date": return DATE_COMPARATOR;
            default: return ID_COMPARATOR;
        }
    }
}
