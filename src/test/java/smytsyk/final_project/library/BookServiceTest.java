package smytsyk.final_project.library;

import org.junit.Assert;
import org.junit.Test;
import smytsyk.final_project.library.service.BookService;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookServiceTest {
    @Test
    public void testIntInList() {
        BookService bs = new BookService();
        List<Integer> list = new ArrayList<>();
        Assert.assertFalse(bs.isListWithNumber(list, 0));
        Assert.assertFalse(bs.isListWithNumber(list, -1));
        Assert.assertFalse(bs.isListWithNumber(list, 1));
        list.add(2);
        list.add(-1);
        list.add(1000);
        list.add(1);
        list.add(-1);
        Assert.assertTrue(bs.isListWithNumber(list, -1));
        Assert.assertFalse(bs.isListWithNumber(list, 0));
        Assert.assertTrue(bs.isListWithNumber(list, 1000));
        Assert.assertTrue(bs.isListWithNumber(list, 1));
    }

    @Test
    public void testValidDate() {
        BookService os = new BookService();
        Assert.assertFalse(os.isValidDate(null));
        Assert.assertFalse(os.isValidDate(""));
        Assert.assertFalse(os.isValidDate("1.1.2011"));
        Assert.assertFalse(os.isValidDate("01.1.2011"));
        Assert.assertFalse(os.isValidDate("1.01.2011"));
        Assert.assertTrue(os.isValidDate("01.01.2011"));
        Assert.assertFalse(os.isValidDate("01.01.2031"));
        Assert.assertTrue(os.isValidDate("11.12.2011"));
        Assert.assertFalse(os.isValidDate("01.00.2011"));
        Assert.assertFalse(os.isValidDate("01.13.2011"));
        Assert.assertFalse(os.isValidDate("00.01.2011"));
        Assert.assertFalse(os.isValidDate("32.01.2011"));
    }

    @Test
    public void testParseDate() {
        BookService os = new BookService();
        Assert.assertEquals(LocalDate.of(2011, 1, 1), os.parseDate("01.01.2011"));
    }

    @Test
    public void testValidName() {
        BookService us = new BookService();
        Assert.assertFalse(us.isValidName(null));
        Assert.assertFalse(us.isValidName(""));
        Assert.assertFalse(us.isValidName("a".repeat(31)));
        Assert.assertTrue(us.isValidName("a;dvdf"));
        Assert.assertFalse(us.isValidName("ddgfd/snyjhf"));
        Assert.assertTrue(us.isValidName("zxcv0mbcdgh"));
        Assert.assertTrue(us.isValidName(":"));
        Assert.assertTrue(us.isValidName("dgfgh5ghfg9!fg"));
        Assert.assertTrue(us.isValidName("dg.fhghd"));
        Assert.assertTrue(us.isValidName("qwe,gfhg"));
        Assert.assertTrue(us.isValidName("a"));
        Assert.assertTrue(us.isValidName("a".repeat(30)));
        Assert.assertTrue(us.isValidName("aAа   Ая-ёиі ы-ho "));
    }

    @Test
    public void testValidAuthor() {
        BookService us = new BookService();
        Assert.assertFalse(us.isValidAuthor(null));
        Assert.assertFalse(us.isValidAuthor(""));
        Assert.assertFalse(us.isValidAuthor("a".repeat(61)));
        Assert.assertFalse(us.isValidAuthor("a;dvdf"));
        Assert.assertFalse(us.isValidAuthor("ddgfd/snyjhf"));
        Assert.assertFalse(us.isValidAuthor("zxcv0mbcdgh"));
        Assert.assertFalse(us.isValidAuthor("="));
        Assert.assertFalse(us.isValidAuthor("dgfgh5ghfg9^fg"));
        Assert.assertFalse(us.isValidAuthor("dg.fhghd"));
        Assert.assertFalse(us.isValidAuthor("qwe_gfhg"));
        Assert.assertTrue(us.isValidAuthor("a"));
        Assert.assertTrue(us.isValidAuthor("a".repeat(60)));
        Assert.assertTrue(us.isValidAuthor("aAа   Ая-ёиі ы-ho "));
    }

    @Test
    public void testValidPublisher() {
        BookService us = new BookService();
        Assert.assertFalse(us.isValidPublisher(null));
        Assert.assertFalse(us.isValidPublisher(""));
        Assert.assertFalse(us.isValidPublisher("a".repeat(31)));
        Assert.assertTrue(us.isValidPublisher("a;dvdf"));
        Assert.assertFalse(us.isValidPublisher("ddgfd/snyjhf"));
        Assert.assertTrue(us.isValidPublisher("zxcv0mbcdgh"));
        Assert.assertTrue(us.isValidPublisher(":"));
        Assert.assertTrue(us.isValidPublisher("dgfgh5ghfg9!fg"));
        Assert.assertTrue(us.isValidPublisher("dg.fhghd"));
        Assert.assertTrue(us.isValidPublisher("qwe,gfhg"));
        Assert.assertTrue(us.isValidPublisher("a"));
        Assert.assertTrue(us.isValidPublisher("a".repeat(30)));
        Assert.assertTrue(us.isValidPublisher("aAа   Ая-ёиі ы-ho "));
    }
}
