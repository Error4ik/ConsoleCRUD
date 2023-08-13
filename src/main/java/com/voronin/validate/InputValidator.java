package com.voronin.validate;

/**
 * @author Alexey Voronin.
 * @since 11.08.2023.
 */
public class InputValidator {
    public static boolean intValidate(String s) {
        if (!s.matches("[-+]?\\d+")) {
            throw new NumberFormatException("Не подходящее число: " + s);
        }
        return true;
    }

    public static boolean stringValidate(String s) {
        if (s.isBlank()) {
            throw new IllegalArgumentException("Поле не должно быть пустым.");
        }
        return true;
    }
}
