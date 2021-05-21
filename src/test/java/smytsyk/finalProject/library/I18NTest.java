package smytsyk.finalProject.library;

import org.junit.Assert;
import org.junit.Test;
import smytsyk.finalProject.library.service.I18N;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class I18NTest {
    @Test
    public void testRu() {
        HttpSession session = new CustomSession("ru");
        ResourceBundle bundle = ResourceBundle.getBundle("resources", new Locale("ru"));
        for (String key : bundle.keySet()) {
            Assert.assertEquals(bundle.getString(key), I18N.translate(key, session));
        }
    }

    @Test
    public void testEn() {
        HttpSession session = new CustomSession("en");
        ResourceBundle bundle = ResourceBundle.getBundle("resources", new Locale("en"));
        for (String key : bundle.keySet()) {
            Assert.assertEquals(bundle.getString(key), I18N.translate(key, session));
        }
    }

    @Test
    public void testDefault() {
        HttpSession session = new CustomSession(null);
        ResourceBundle bundle = ResourceBundle.getBundle("resources", new Locale("ru"));
        for (String key : bundle.keySet()) {
            Assert.assertEquals(bundle.getString(key), I18N.translate(key, session));
        }
    }
}

class CustomSession implements HttpSession {
    private final String locale;

    public CustomSession(String loc) {
        locale = loc;
    }

    @Override
    public long getCreationTime() {
        return 0;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public long getLastAccessedTime() {
        return 0;
    }

    @Override
    public ServletContext getServletContext() {
        return null;
    }

    @Override
    public void setMaxInactiveInterval(int i) {

    }

    @Override
    public int getMaxInactiveInterval() {
        return 0;
    }

    @Override
    public HttpSessionContext getSessionContext() {
        return null;
    }

    @Override
    public Object getAttribute(String s) {
        if ("lang".equals(s)) return locale;
        return null;
    }

    @Override
    public Object getValue(String s) {
        return null;
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return null;
    }

    @Override
    public String[] getValueNames() {
        return new String[0];
    }

    @Override
    public void setAttribute(String s, Object o) {

    }

    @Override
    public void putValue(String s, Object o) {

    }

    @Override
    public void removeAttribute(String s) {

    }

    @Override
    public void removeValue(String s) {

    }

    @Override
    public void invalidate() {

    }

    @Override
    public boolean isNew() {
        return false;
    }
}
