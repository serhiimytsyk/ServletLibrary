package smytsyk.final_project.library;

import org.junit.Assert;
import org.junit.Test;
import smytsyk.final_project.library.controller.command.impl.BookUtil;
import smytsyk.final_project.library.service.BookService;


import java.time.LocalDate;

public class BookUtilTest {
    @Test
    public void testValidDate() {
        Assert.assertFalse(BookUtil.isValidDate(null));
        Assert.assertFalse(BookUtil.isValidDate(""));
        Assert.assertFalse(BookUtil.isValidDate("1.1.2011"));
        Assert.assertFalse(BookUtil.isValidDate("01.1.2011"));
        Assert.assertFalse(BookUtil.isValidDate("1.01.2011"));
        Assert.assertTrue(BookUtil.isValidDate("01.01.2011"));
        Assert.assertFalse(BookUtil.isValidDate("01.01.2031"));
        Assert.assertTrue(BookUtil.isValidDate("11.12.2011"));
        Assert.assertFalse(BookUtil.isValidDate("01.00.2011"));
        Assert.assertFalse(BookUtil.isValidDate("01.13.2011"));
        Assert.assertFalse(BookUtil.isValidDate("00.01.2011"));
        Assert.assertFalse(BookUtil.isValidDate("32.01.2011"));
    }

    @Test
    public void testParseDate() {
        BookService BookUtil = new BookService();
        Assert.assertEquals(LocalDate.of(2011, 1, 1), BookUtil.parseDate("01.01.2011"));
    }

    @Test
    public void testValidName() {
        Assert.assertFalse(BookUtil.isValidName(null));
        Assert.assertFalse(BookUtil.isValidName(""));
        Assert.assertFalse(BookUtil.isValidName("a".repeat(31)));
        Assert.assertTrue(BookUtil.isValidName("a;dvdf"));
        Assert.assertFalse(BookUtil.isValidName("ddgfd/snyjhf"));
        Assert.assertTrue(BookUtil.isValidName("zxcv0mbcdgh"));
        Assert.assertTrue(BookUtil.isValidName(":"));
        Assert.assertTrue(BookUtil.isValidName("dgfgh5ghfg9!fg"));
        Assert.assertTrue(BookUtil.isValidName("dg.fhghd"));
        Assert.assertTrue(BookUtil.isValidName("qwe,gfhg"));
        Assert.assertTrue(BookUtil.isValidName("a"));
        Assert.assertTrue(BookUtil.isValidName("a".repeat(30)));
        Assert.assertTrue(BookUtil.isValidName("aAа   Ая-ёиі ы-ho "));
    }

    @Test
    public void testValidAuthor() {
        Assert.assertFalse(BookUtil.isValidAuthor(null));
        Assert.assertFalse(BookUtil.isValidAuthor(""));
        Assert.assertFalse(BookUtil.isValidAuthor("a".repeat(61)));
        Assert.assertFalse(BookUtil.isValidAuthor("a;dvdf"));
        Assert.assertFalse(BookUtil.isValidAuthor("ddgfd/snyjhf"));
        Assert.assertFalse(BookUtil.isValidAuthor("zxcv0mbcdgh"));
        Assert.assertFalse(BookUtil.isValidAuthor("="));
        Assert.assertFalse(BookUtil.isValidAuthor("dgfgh5ghfg9^fg"));
        Assert.assertFalse(BookUtil.isValidAuthor("dg.fhghd"));
        Assert.assertFalse(BookUtil.isValidAuthor("qwe_gfhg"));
        Assert.assertTrue(BookUtil.isValidAuthor("a"));
        Assert.assertTrue(BookUtil.isValidAuthor("a".repeat(60)));
        Assert.assertTrue(BookUtil.isValidAuthor("aAа   Ая-ёиі ы-ho "));
    }

    @Test
    public void testValidPublisher() {
        Assert.assertFalse(BookUtil.isValidPublisher(null));
        Assert.assertFalse(BookUtil.isValidPublisher(""));
        Assert.assertFalse(BookUtil.isValidPublisher("a".repeat(31)));
        Assert.assertTrue(BookUtil.isValidPublisher("a;dvdf"));
        Assert.assertFalse(BookUtil.isValidPublisher("ddgfd/snyjhf"));
        Assert.assertTrue(BookUtil.isValidPublisher("zxcv0mbcdgh"));
        Assert.assertTrue(BookUtil.isValidPublisher(":"));
        Assert.assertTrue(BookUtil.isValidPublisher("dgfgh5ghfg9!fg"));
        Assert.assertTrue(BookUtil.isValidPublisher("dg.fhghd"));
        Assert.assertTrue(BookUtil.isValidPublisher("qwe,gfhg"));
        Assert.assertTrue(BookUtil.isValidPublisher("a"));
        Assert.assertTrue(BookUtil.isValidPublisher("a".repeat(30)));
        Assert.assertTrue(BookUtil.isValidPublisher("aAа   Ая-ёиі ы-ho "));
    }
}
