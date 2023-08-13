package com.voronin.view;

import com.voronin.controller.WriterController;
import com.voronin.utils.Input;

/**
 * @author Alexey Voronin.
 * @since 08.08.2023.
 */
public class WriterView {
    private final WriterController writerController;
    private final Input input;

    public WriterView(WriterController writerController, Input input) {
        this.writerController = writerController;
        this.input = input;
    }

    public void save() {
        String writeFirstName = this.input.getInput("Введите имя автора:\n");
        String writeLastName = this.input.getInput("Введите фамилию автора:\n");
        System.out.println("Автор был создан: " + writerController.save(writeFirstName, writeLastName));
    }

    public void findById() {
        String writerId = this.input.getInput("Введите айди автора:\n");
        System.out.println(writerController.findById(writerId));
    }

    public void findAll() {
        System.out.println("Все авторы:\n" + writerController.findAll());
    }

    public void update() {
        String writerId = this.input.getInput("Введите айди автора которого нужно изменить:\n");
        String writeFirstName = this.input.getInput("Введите имя автора:\n");
        String writeLastName = this.input.getInput("Введите фамилию автора:\n");
        System.out.println("Автор был изменен: " + writerController.update(writerId, writeFirstName, writeLastName));
    }

    public void delete() {
        String writerId = this.input.getInput("Введите айди автора которого нужно удалить:\n");
        writerController.delete(writerId);
        System.out.println("Автор удален");
    }

    public void addPostToWriter() {
        String writerId = this.input.getInput("Введите айди автора которому нужно добавить пост:\n");
        String postId = this.input.getInput("Введите айди поста которой нужно добавить автору:\n");
        writerController.addPostToWriter(writerId, postId);
        System.out.println("Пост добавлен");
    }

    public void removePostFromWriter() {
        String writerId = this.input.getInput("Введите айди автора у которому нужно удалить пост:\n");
        String postId = this.input.getInput("Введите айди поста которой нужно удалить у автора:\n");
        writerController.removePostFromWriter(writerId, postId);
        System.out.println("Пост удален");
    }
}
