package com.voronin.view;

import com.voronin.controller.LabelController;
import com.voronin.utils.Input;

/**
 * @author Alexey Voronin.
 * @since 09.08.2023.
 */
public class LabelView {

    private final LabelController labelController;
    private final Input input;

    public LabelView(LabelController labelController, Input input) {
        this.labelController = labelController;
        this.input = input;
    }

    public void save() {
        String labelName = this.input.getInput("Введите название метки:\n");
        System.out.println("Метка была создана: " + labelController.save(labelName));
    }

    public void findById() {
        String labelId = this.input.getInput("Введите айди метки:\n");
        System.out.println(labelController.findById(labelId));
    }

    public void findAll() {
        System.out.println("Все метки:\n" + labelController.findAll());
    }

    public void update() {
        String labelId = this.input.getInput("Введите айди метки которую нужно изменить:\n");
        String labelName = this.input.getInput("Введите новое название метки:\n");
        System.out.println("Метка была изменена: " + labelController.update(labelId, labelName));
    }

    public void delete() {
        String labelId = this.input.getInput("Введите айди метки для удаления:\n");
        labelController.delete(labelId);
        System.out.println("Метка удалена.");
    }
}
