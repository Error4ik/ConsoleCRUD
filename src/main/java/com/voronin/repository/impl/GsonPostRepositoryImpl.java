package com.voronin.repository.impl;

import com.google.gson.reflect.TypeToken;
import com.voronin.model.Label;
import com.voronin.model.Post;
import com.voronin.model.PostStatus;
import com.voronin.model.Status;
import com.voronin.repository.PostRepository;
import com.voronin.repository.WriterRepository;
import com.voronin.utils.FileUtils;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Alexey Voronin.
 * @since 10.08.2023.
 */
public class GsonPostRepositoryImpl implements PostRepository {
    private final static Path POSTS_FILE = Path.of("src", "main", "resources", "posts.json");
    private final FileUtils<Path, Post> fileUtils = new FileUtils<>(POSTS_FILE, TypeToken.get(Post[].class));
    private final WriterRepository writerRepository;

    public GsonPostRepositoryImpl(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    @Override
    public Post save(Post post) {
        List<Post> posts = fileUtils.getFromFile();
        int nextId = getNewId(posts);

        post.setId(nextId);
        post.setCreated(LocalDateTime.now());
        post.setUpdated(LocalDateTime.now());
        post.setLabels(new ArrayList<>());
        post.setStatus(PostStatus.UNDER_REVIEW);
        posts.add(post);

        fileUtils.writeToFile(posts);
        return post;
    }

    @Override
    public Post findById(Integer id) {
        List<Post> posts = fileUtils.getFromFile();
        Optional<Post> first = posts.stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst();

        return first.orElseThrow(() -> new IllegalArgumentException("Не существующий ID: " + id));
    }

    @Override
    public List<Post> findAll() {
        List<Post> posts = fileUtils.getFromFile();
        posts = posts.stream()
                .filter(p -> p.getStatus() != PostStatus.DELETED)
                .collect(Collectors.toList());

        return posts;
    }

    @Override
    public Post update(Post post) {
        List<Post> posts = fileUtils.getFromFile();
        Optional<Post> first = posts.stream()
                .filter(p -> Objects.equals(p.getId(), post.getId()))
                .findFirst();

        Post p;
        if (first.isPresent()) {
            p = first.get();
            p.setContent(post.getContent());
            p.setUpdated(LocalDateTime.now());
            fileUtils.writeToFile(posts);
            writerRepository.updatePostInWriter(p);
        } else {
            throw new IllegalArgumentException("Не существующий ID: " + post);
        }
        return p;
    }

    @Override
    public void deleteById(Integer id) {
        List<Post> posts = fileUtils.getFromFile();
        Optional<Post> first = posts.stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst();

        if (first.isPresent()) {
            Post p = first.get();
            p.setStatus(PostStatus.DELETED);
            p.setUpdated(LocalDateTime.now());
            fileUtils.writeToFile(posts);
            writerRepository.updatePostInWriter(p);
        } else {
            throw new IllegalArgumentException("Не существующий ID: " + id);
        }
    }

    @Override
    public void addLabelToPost(Post post, Label label) {
        List<Post> posts = fileUtils.getFromFile();
        Optional<Post> first = posts.stream()
                .filter(p -> Objects.equals(p.getId(), post.getId()))
                .findFirst();

        if (first.isPresent()) {
            Post p = first.get();
            if (!p.getLabels().contains(label)) {
                p.getLabels().add(label);
            }
            writerRepository.updatePostInWriter(p);
        }

        fileUtils.writeToFile(posts);
    }

    @Override
    public void removeLabelFromPost(Post post, Label label) {
        List<Post> posts = fileUtils.getFromFile();
        Optional<Post> first = posts.stream()
                .filter(p -> Objects.equals(p.getId(), post.getId()))
                .findFirst();

        if (first.isPresent()) {
            Post p = first.get();
            if (p.getLabels().contains(label)) {
                p.getLabels().remove(label);
                writerRepository.updatePostInWriter(p);
            }
        }
        fileUtils.writeToFile(posts);
    }

    @Override
    public void updateLabelInPost(Label label) {
        List<Post> posts = fileUtils.getFromFile();

        List<Label> labels;
        for (Post p : posts) {
            labels = new ArrayList<>();
            for (Label l : p.getLabels()) {
                if (!l.getId().equals(label.getId())) {
                    labels.add(l);
                } else {
                    if (label.getStatus() != Status.DELETED) {
                        labels.add(label);
                    }
                }
            }
            p.setLabels(labels);
            writerRepository.updatePostInWriter(p);
        }
        fileUtils.writeToFile(posts);
    }

    private static Integer getNewId(List<Post> posts) {
        Optional<Integer> maxId = posts.stream()
                .map(Post::getId)
                .max(Integer::compare);

        return maxId.orElse(0) + 1;
    }
}
