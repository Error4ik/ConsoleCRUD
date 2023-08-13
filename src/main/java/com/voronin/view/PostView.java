package com.voronin.view;

import com.voronin.controller.PostController;
import com.voronin.utils.Input;

/**
 * @author Alexey Voronin.
 * @since 10.08.2023.
 */
public class PostView {

    private final PostController postController;
    private final Input input;

    public PostView(PostController postController, Input input) {
        this.postController = postController;
        this.input = input;
    }

    public void save() {
        String labelName = this.input.getInput("Введите название поста:\n");
        System.out.println("Пост был создан: " + postController.save(labelName));
    }

    public void findById() {
        String postId = this.input.getInput("Введите айди поста:\n");
        System.out.println( postController.findById(postId));
    }

    public void findAll() {
        System.out.println("Все посты:\n" + postController.findAll());
    }

    public void update() {
        String postId = this.input.getInput("Введите айди поста для изменения:\n");
        String postContent = this.input.getInput("Введите новый текст для поста:\n");
        System.out.println("Пост был изменен: " + postController.update(postId, postContent));
    }

    public void delete() {
        String postId = this.input.getInput("Введите айди поста для удаления:\n");
        postController.delete(postId);
        System.out.println("Пост удален.");
    }

    public void addLabelToPost() {
        String postId = this.input.getInput("Введите айди поста к которому нужно добавить метку:\n");
        String labelId = this.input.getInput("Введите айди метки которую нужно добавить:\n");
        postController.addLabelToPost(postId, labelId);
        System.out.println("Метка добавлена.");
    }

    public void removeLabelFromPost() {
        String postId = this.input.getInput("Введите айди поста у которого нужно удалить метку:\n");
        String labelId = this.input.getInput("Введите айди метки которую нужно удалить:\n");
        postController.removeLabelFromPost(postId, labelId);
    }
}
