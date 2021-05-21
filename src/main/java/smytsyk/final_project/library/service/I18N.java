package smytsyk.final_project.library.service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class for internationalization
 */
public class I18N {
    private static final List<String> LOCALES = new ArrayList<>();

    static {
        LOCALES.add("en");
        LOCALES.add("ru");
    }

    /**
     * Translates word to language of session
     */
    public static String translate(String word, HttpSession session) {
        String lang = (String) session.getAttribute("lang");
        ResourceBundle bundle;
        if (LOCALES.contains(lang)) {
            bundle = ResourceBundle.getBundle("resources", new Locale(lang));
        } else {
            bundle = ResourceBundle.getBundle("resources", new Locale("ru"));
        }
        return bundle.getString(word);
    }
}
