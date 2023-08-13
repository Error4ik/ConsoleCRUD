package com.voronin.actions;

import com.voronin.utils.Input;
import com.voronin.validate.InputValidator;
import com.voronin.view.PostView;

/**
 * @author Alexey Voronin.
 * @since 13.08.2023.
 */
public class PostActions implements Actions {

    private final Input input;
    private final PostView postView;

    public PostActions(Input input, PostView postView) {
        this.input = input;
        this.postView = postView;
    }

    @Override
    public void showAction() {
        int menuPostActions;
        do {
            String subInputString = input.getInput(showPostMenu());
            InputValidator.intValidate(subInputString);
            menuPostActions = Integer.parseInt(subInputString);
            switch (menuPostActions) {
                case 1: {
                    postView.save();
                    break;
                }
                case 2: {
                    postView.findById();
                    break;
                }
                case 3: {
                    postView.findAll();
                    break;
                }
                case 4: {
                    postView.update();
                    break;
                }
                case 5: {
                    postView.delete();
                    break;
                }
                case 6: {
                    postView.addLabelToPost();
                    break;
                }
                case 7: {
                    postView.removeLabelFromPost();
                    break;
                }
                default: {
                    if (menuPostActions != 0) {
                        System.out.println("---------Неверный пункт меню.---------");
                    }
                }
            }
        } while (menuPostActions != 0);
    }

    private String showPostMenu() {
        return String.format(
                "Выюерите действия:\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n",
                "1: Создать Пост",
                "2: Получить Пост по ID",
                "3: Получить список Постов",
                "4: Изменить Пост",
                "5: Удалить Пост",
                "6: Добавить Метку к Посту",
                "7: Удалить Метку у Поста",
                "0: Для выхода в верхнее меню");
    }
}
