package com.voronin.actions;

import com.voronin.utils.Input;
import com.voronin.validate.InputValidator;
import com.voronin.view.LabelView;

/**
 * @author Alexey Voronin.
 * @since 13.08.2023.
 */
public class LabelActions implements Actions {

    private final Input input;
    private final LabelView labelView;

    public LabelActions(Input input, LabelView labelView) {
        this.input = input;
        this.labelView = labelView;
    }

    @Override
    public void showAction() {
        int menuLabelActions;
        do {
            String subInputString = input.getInput(showMenu());
            InputValidator.intValidate(subInputString);
            menuLabelActions = Integer.parseInt(subInputString);
            switch (menuLabelActions) {
                case 1: {
                    labelView.save();
                    break;
                }
                case 2: {
                    labelView.findById();
                    break;
                }
                case 3: {
                    labelView.findAll();
                    break;
                }
                case 4: {
                    labelView.update();
                    break;
                }
                case 5: {
                    labelView.delete();
                    break;
                }
                default: {
                    if (menuLabelActions != 0) {
                        System.out.println("---------Неверный пункт меню.---------");
                    }
                }
            }
        } while (menuLabelActions != 0);
    }

    private String showMenu() {
        return String.format(
                "Выюерите действия:\n%s\n%s\n%s\n%s\n%s\n%s\n",
                "1: Создать Метку",
                "2: Получить Метку по ID",
                "3: Получить список Меток",
                "4: Изменить Метку",
                "5: Удалить Метку",
                "0: Для выхода в верхнее меню");
    }
}
