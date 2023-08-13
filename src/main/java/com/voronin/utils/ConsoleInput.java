package com.voronin.utils;

import java.util.Scanner;

/**
 * @author Alexey Voronin.
 * @since 08.08.2023.
 */
public class ConsoleInput implements Input {

    private final static Scanner scanner = new Scanner(System.in);

    @Override
    public String getInput(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }
}
