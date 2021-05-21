package smytsyk.final_project.library;

import org.junit.Assert;
import org.junit.Test;
import smytsyk.final_project.library.service.UserService;

public class UserServiceTest {
    @Test
    public void testLogin() {
        UserService us = new UserService();
        Assert.assertFalse(us.isValidLogin(null));
        Assert.assertFalse(us.isValidLogin(""));
        Assert.assertFalse(us.isValidLogin("a".repeat(31)));
        Assert.assertFalse(us.isValidLogin("a;dvdf"));
        Assert.assertFalse(us.isValidLogin("ddgfd/snyjhf"));
        Assert.assertFalse(us.isValidLogin("zxcv*mbcdgh"));
        Assert.assertFalse(us.isValidLogin("="));
        Assert.assertFalse(us.isValidLogin("dgfgh5ghfg5545^fg"));
        Assert.assertFalse(us.isValidLogin("dg!fhghd"));
        Assert.assertFalse(us.isValidLogin("qwe?gfhg"));
        Assert.assertTrue(us.isValidLogin("a"));
        Assert.assertTrue(us.isValidLogin("a".repeat(30)));
        Assert.assertTrue(us.isValidLogin("aAаАяёиіы0123456789.-_ho"));
    }

    @Test
    public void testName() {
        UserService us = new UserService();
        Assert.assertFalse(us.isValidName(null));
        Assert.assertFalse(us.isValidName(""));
        Assert.assertFalse(us.isValidName("a".repeat(31)));
        Assert.assertFalse(us.isValidName("a;dvdf"));
        Assert.assertFalse(us.isValidName("ddgfd/snyjhf"));
        Assert.assertFalse(us.isValidName("zxcv0mbcdgh"));
        Assert.assertFalse(us.isValidName("="));
        Assert.assertFalse(us.isValidName("dgfgh5ghfg9^fg"));
        Assert.assertFalse(us.isValidName("dg.fhghd"));
        Assert.assertFalse(us.isValidName("qwe_gfhg"));
        Assert.assertTrue(us.isValidName("a"));
        Assert.assertTrue(us.isValidName("a".repeat(30)));
        Assert.assertTrue(us.isValidName("aAа   Ая-ёиі ы-ho "));
    }

    @Test
    public void testPassword() {
        UserService us = new UserService();
        Assert.assertFalse(us.isValidPassword(null));
        Assert.assertFalse(us.isValidPassword(""));
        Assert.assertFalse(us.isValidPassword("A".repeat(31)));
        Assert.assertTrue(us.isValidPassword("A".repeat(30)));
        Assert.assertTrue(us.isValidPassword("120-.*/,!@$%^&АаЫІ ыёA vnbda3"));
    }

    @Test
    public void testEmail() {
        UserService us = new UserService();
        Assert.assertFalse(us.isValidEmail(null));
        Assert.assertFalse(us.isValidEmail(""));
        Assert.assertFalse(us.isValidEmail("a".repeat(31)));
        Assert.assertFalse(us.isValidEmail("a".repeat(30)));
        Assert.assertFalse(us.isValidEmail("a".repeat(29)));
        Assert.assertFalse(us.isValidEmail("aaaaa@aaaaa"));
        Assert.assertFalse(us.isValidEmail("aaaaa@aaaaa."));
        Assert.assertTrue(us.isValidEmail("aaaaa@aaaaa.aaaa"));
        Assert.assertTrue(us.isValidEmail("9яЯыa-0a.a.a1a@aaя1a.aaaa"));
        Assert.assertFalse(us.isValidEmail("9яЯыa-0a.a.a1a@я0a.aaa.aaaa"));
    }
}
