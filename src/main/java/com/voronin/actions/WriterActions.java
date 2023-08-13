package com.voronin.actions;

import com.voronin.utils.Input;
import com.voronin.validate.InputValidator;
import com.voronin.view.WriterView;

/**
 * @author Alexey Voronin.
 * @since 13.08.2023.
 */
public class WriterActions implements Actions {

    private final Input input;
    private final WriterView writerView;

    public WriterActions(Input input, WriterView writerView) {
        this.input = input;
        this.writerView = writerView;
    }

    @Override
    public void showAction() {
        int menuWriterActions;
        do {
            String subInputString = input.getInput(showWriterMenu());
            InputValidator.intValidate(subInputString);
            menuWriterActions = Integer.parseInt(subInputString);
            switch (menuWriterActions) {
                case 1: {
                    writerView.save();
                    break;
                }
                case 2: {
                    writerView.findById();
                    break;
                }
                case 3: {
                    writerView.findAll();
                    break;
                }
                case 4: {
                    writerView.update();
                    break;
                }
                case 5: {
                    writerView.delete();
                    break;
                }
                case 6: {
                    writerView.addPostToWriter();
                    break;
                }
                case 7: {
                    writerView.removePostFromWriter();
                    break;
                }
                default: {
                    System.out.println("---------Неверный пункт меню.---------");
                }
            }
        } while (menuWriterActions != 0);
    }

    private String showWriterMenu() {
        return String.format(
                "Выюерите действия:\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n",
                "1: Создать Автора",
                "2: Получить Автора по ID",
                "3: Получить список Авторов",
                "4: Изменить Автора",
                "5: Удалить Автора",
                "6: Добавить Пост к Автору",
                "7: Удалить Пост у Автора",
                "0: Для выхода в верхнее меню");
    }
}
