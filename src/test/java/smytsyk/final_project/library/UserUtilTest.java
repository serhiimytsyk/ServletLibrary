package smytsyk.final_project.library;

import org.junit.Assert;
import org.junit.Test;
import smytsyk.final_project.library.controller.command.impl.UserUtil;

public class UserUtilTest {
    @Test
    public void testLogin() {
        Assert.assertFalse(UserUtil.isValidLogin(null));
        Assert.assertFalse(UserUtil.isValidLogin(""));
        Assert.assertFalse(UserUtil.isValidLogin("a".repeat(31)));
        Assert.assertFalse(UserUtil.isValidLogin("a;dvdf"));
        Assert.assertFalse(UserUtil.isValidLogin("ddgfd/snyjhf"));
        Assert.assertFalse(UserUtil.isValidLogin("zxcv*mbcdgh"));
        Assert.assertFalse(UserUtil.isValidLogin("="));
        Assert.assertFalse(UserUtil.isValidLogin("dgfgh5ghfg5545^fg"));
        Assert.assertFalse(UserUtil.isValidLogin("dg!fhghd"));
        Assert.assertFalse(UserUtil.isValidLogin("qwe?gfhg"));
        Assert.assertTrue(UserUtil.isValidLogin("a"));
        Assert.assertTrue(UserUtil.isValidLogin("a".repeat(30)));
        Assert.assertTrue(UserUtil.isValidLogin("aAаАяёиіы0123456789.-_ho"));
    }

    @Test
    public void testName() {
        Assert.assertFalse(UserUtil.isValidName(null));
        Assert.assertFalse(UserUtil.isValidName(""));
        Assert.assertFalse(UserUtil.isValidName("a".repeat(31)));
        Assert.assertFalse(UserUtil.isValidName("a;dvdf"));
        Assert.assertFalse(UserUtil.isValidName("ddgfd/snyjhf"));
        Assert.assertFalse(UserUtil.isValidName("zxcv0mbcdgh"));
        Assert.assertFalse(UserUtil.isValidName("="));
        Assert.assertFalse(UserUtil.isValidName("dgfgh5ghfg9^fg"));
        Assert.assertFalse(UserUtil.isValidName("dg.fhghd"));
        Assert.assertFalse(UserUtil.isValidName("qwe_gfhg"));
        Assert.assertTrue(UserUtil.isValidName("a"));
        Assert.assertTrue(UserUtil.isValidName("a".repeat(30)));
        Assert.assertTrue(UserUtil.isValidName("aAа   Ая-ёиі ы-ho "));
    }

    @Test
    public void testPassword() {
        Assert.assertFalse(UserUtil.isValidPassword(null));
        Assert.assertFalse(UserUtil.isValidPassword(""));
        Assert.assertFalse(UserUtil.isValidPassword("A".repeat(31)));
        Assert.assertTrue(UserUtil.isValidPassword("A".repeat(30)));
    }

    @Test
    public void testEmail() {
        Assert.assertFalse(UserUtil.isValidEmail(null));
        Assert.assertFalse(UserUtil.isValidEmail(""));
        Assert.assertFalse(UserUtil.isValidEmail("a".repeat(31)));
        Assert.assertFalse(UserUtil.isValidEmail("a".repeat(30)));
        Assert.assertFalse(UserUtil.isValidEmail("a".repeat(29)));
        Assert.assertFalse(UserUtil.isValidEmail("aaaaa@aaaaa"));
        Assert.assertFalse(UserUtil.isValidEmail("aaaaa@aaaaa."));
        Assert.assertTrue(UserUtil.isValidEmail("aaaaa@aaaaa.aaaa"));
        Assert.assertTrue(UserUtil.isValidEmail("9яЯыa-0a.a.a1a@aaя1a.aaaa"));
        Assert.assertFalse(UserUtil.isValidEmail("9яЯыa-0a.a.a1a@я0a.aaa.aaaa"));
    }
}
