package smytsyk.finalProject.library;

import org.junit.Assert;
import org.junit.Test;
import smytsyk.finalProject.library.entitiy.Book;
import smytsyk.finalProject.library.entitiy.Entity;
import smytsyk.finalProject.library.entitiy.Order;
import smytsyk.finalProject.library.entitiy.User;

import java.time.LocalDate;

public class EntityTest {
    @Test
    public void testEntity() {
        Entity entity = new Book();
        entity.setId(1);
        Assert.assertEquals(1, entity.getId());
        entity.setId(0);
        Assert.assertEquals(0, entity.getId());
        entity.setId(Integer.MAX_VALUE);
        Assert.assertEquals(Integer.MAX_VALUE, entity.getId());
        entity = new User();
        entity.setId(-1);
        Assert.assertEquals(-1, entity.getId());
        entity.setId(Integer.MIN_VALUE);
        Assert.assertEquals(Integer.MIN_VALUE, entity.getId());
        entity = new Order();
        entity.setId(5);
        Assert.assertEquals(5, entity.getId());
        entity.setId(10);
        Assert.assertEquals(10, entity.getId());
    }

    @Test
    public void testUser() {
        User user = new User();
        user.setLogin("aaaaaaaaaaaaaaaaaaaaaaaa");
        Assert.assertEquals("aaaaaaaaaaaaaaaaaaaaaaaa", user.getLogin());
        user.setLogin("login");
        Assert.assertEquals("login", user.getLogin());
        user.setPassword("password");
        Assert.assertEquals("password", user.getPassword());
        user.setFirstName("first");
        Assert.assertEquals("first", user.getFirstName());
        user.setLastName("last");
        Assert.assertEquals("last", user.getLastName());
        user.setEmail("email");
        Assert.assertEquals("email", user.getEmail());
        user.setRoleId(5);
        Assert.assertEquals(5, user.getRoleId());
        user.setRoleId(1);
        Assert.assertEquals(1, user.getRoleId());

        Assert.assertEquals("login", user.getLogin());
        Assert.assertEquals("password", user.getPassword());
        Assert.assertEquals("first", user.getFirstName());
        Assert.assertEquals("last", user.getLastName());
        Assert.assertEquals("email", user.getEmail());
        Assert.assertEquals(1, user.getRoleId());
    }

    @Test
    public void testOrder() {
        Order order = new Order();
        order.setBookId(1);
        Assert.assertEquals(1, order.getBookId());
        order.setReaderId(2);
        Assert.assertEquals(2, order.getReaderId());
        order.setOrderStatusId(3);
        Assert.assertEquals(3, order.getOrderStatusId());
        LocalDate date = LocalDate.of(2020, 4, 26);
        order.setReturnDate(date);
        Assert.assertEquals(date, order.getReturnDate());
    }

    @Test
    public void testBook() {
        Book book = new Book();
        book.setName("xxx");
        Assert.assertEquals("xxx", book.getName());
        book.setName("name");
        Assert.assertEquals("name", book.getName());
        book.setAuthor("author");
        Assert.assertEquals("author", book.getAuthor());
        book.setPublisher("pub");
        Assert.assertEquals("pub", book.getPublisher());
        LocalDate date = LocalDate.of(2020, 1, 25);
        book.setPublicationDate(date);
        Assert.assertEquals(date, book.getPublicationDate());

        Assert.assertEquals("name", book.getName());
        Assert.assertEquals("author", book.getAuthor());
        Assert.assertEquals("pub", book.getPublisher());
        Assert.assertEquals(date, book.getPublicationDate());
    }
}
