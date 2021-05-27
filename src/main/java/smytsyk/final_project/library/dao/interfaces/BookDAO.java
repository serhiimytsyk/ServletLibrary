package smytsyk.final_project.library.dao.interfaces;

import smytsyk.final_project.library.entitiy.Book;

import java.util.List;

/**
 * DAO of Books
 */
public interface BookDAO extends AbstractDao<Book>{
    List<Book> getFreeBooks();
}
