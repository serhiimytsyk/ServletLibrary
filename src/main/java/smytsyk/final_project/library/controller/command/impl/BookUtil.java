package smytsyk.final_project.library.controller.command.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookUtil {
    /**
     * Checks if name is valid
     */
    public static boolean isValidName(String name) {
        if (name == null || name.isEmpty() || name.length() > 30) return false;
        return name.matches("[-,.:;!?\\p{IsAlphabetic}\\s0-9]+");
    }

    /**
     * Checks if author is valid
     */
    public static boolean isValidAuthor(String name) {
        if (name == null || name.isEmpty() || name.length() > 60) return false;
        return name.matches("[-\\p{IsAlphabetic}\\s]+");
    }

    /**
     * Checks if publisher is valid
     */
    public static boolean isValidPublisher(String name) {
        if (name == null || name.isEmpty() || name.length() > 30) return false;
        return name.matches("[-,.:;!?\\p{IsAlphabetic}\\s0-9]+");
    }

    /**
     * Checks if date is valid
     */
    public static boolean isValidDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate localDate = LocalDate.parse(date, formatter);
            if (localDate.isAfter(LocalDate.now())) return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
