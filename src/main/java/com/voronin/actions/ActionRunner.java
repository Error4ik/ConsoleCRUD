package com.voronin.actions;

import com.voronin.utils.Input;
import com.voronin.validate.InputValidator;

import java.util.List;

/**
 * @author Alexey Voronin.
 * @since 13.08.2023.
 */
public class ActionRunner {

    private final Input input;
    private final List<Actions> actions;

    public ActionRunner(Input input, List<Actions> actions) {
        this.input = input;
        this.actions = actions;
    }

    public void startMenu() {
        int value = 0;
        do {
            try {
                String inputString = input.getInput(showMainMenu());
                InputValidator.intValidate(inputString);
                value = Integer.parseInt(inputString);

                if (value > 0 && value <= actions.size()) {
                    actions.get(value - 1).showAction();
                } else {
                    System.out.println("---------Неверный пункт меню.---------");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("Попробуйте снова.");
            }
        } while (value != 0);
    }

    private static String showMainMenu() {
        return String.format(
                "Выюерите действия:\n%s\n%s\n%s\n%s\n",
                "1: Операции над Label",
                "2: Операции над Post",
                "3: Операции над Writer",
                "0: Выход");
    }
}
