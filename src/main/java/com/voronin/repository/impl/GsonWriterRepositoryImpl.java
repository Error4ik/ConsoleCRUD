package com.voronin.repository.impl;

import com.google.gson.reflect.TypeToken;
import com.voronin.model.Post;
import com.voronin.model.PostStatus;
import com.voronin.model.Status;
import com.voronin.model.Writer;
import com.voronin.repository.WriterRepository;
import com.voronin.utils.FileUtils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Alexey Voronin.
 * @since 08.08.2023.
 */
public class GsonWriterRepositoryImpl implements WriterRepository {

    private final static Path WRITERS_FILE = Path.of("src", "main", "resources", "writers.json");
    private final FileUtils<Path, Writer> fileUtils = new FileUtils<>(WRITERS_FILE, TypeToken.get(Writer[].class));

    @Override
    public Writer save(Writer writer) {
        List<Writer> writers = fileUtils.getFromFile();
        int nextId = getNewId(writers);

        writer.setId(nextId);
        writer.setPosts(new ArrayList<>());
        writer.setStatus(Status.ACTIVE);
        writers.add(writer);

        fileUtils.writeToFile(writers);
        return writer;
    }

    @Override
    public Writer findById(Integer id) {
        List<Writer> writers = fileUtils.getFromFile();
        Optional<Writer> first = writers.stream()
                .filter(w -> Objects.equals(w.getId(), id))
                .findFirst();

        return first.orElseThrow(() -> new IllegalArgumentException("Не существующий ID: " + id));
    }

    @Override
    public List<Writer> findAll() {
        List<Writer> writers = fileUtils.getFromFile();
        writers = writers.stream()
                .filter(w -> w.getStatus() != Status.DELETED)
                .collect(Collectors.toList());

        return writers;
    }

    @Override
    public Writer update(Writer writer) {
        List<Writer> writers = fileUtils.getFromFile();
        Writer first = writers.stream()
                .filter(w -> Objects.equals(w.getId(), writer.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Не существующий ID: " + writer));

        first.setFirstname(writer.getFirstname());
        first.setLastname(writer.getLastname());
        fileUtils.writeToFile(writers);

        return first;
    }

    @Override
    public void deleteById(Integer id) {
        List<Writer> writers = fileUtils.getFromFile();
        Writer first = writers.stream()
                .filter(w -> Objects.equals(w.getId(), id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Не существующий ID: " + id));

        first.setStatus(Status.DELETED);
        fileUtils.writeToFile(writers);
    }

    @Override
    public void addPostToWriter(Writer writer, Post post) {
        List<Writer> writers = fileUtils.getFromFile();
        for (Writer w : writers) {
            if (w.getId().equals(writer.getId())) {
                if (!w.getPosts().contains(post)) {
                    w.getPosts().add(post);
                }
                break;
            }
        }
        fileUtils.writeToFile(writers);
    }

    @Override
    public void removePostFromWriter(Writer writer, Post post) {
        List<Writer> writers = fileUtils.getFromFile();
        for (Writer w : writers) {
            if (w.getId().equals(writer.getId())) {
                w.getPosts().remove(post);
                break;
            }
        }
        fileUtils.writeToFile(writers);
    }

    @Override
    public void updatePostInWriter(Post post) {
        List<Writer> writers = fileUtils.getFromFile();
        for (Writer w : writers) {
            List<Post> posts = new ArrayList<>();
            for (Post p : w.getPosts()) {
                if (!Objects.equals(p.getId(), post.getId())) {
                    posts.add(p);
                } else {
                    if (post.getStatus() != PostStatus.DELETED) {
                        posts.add(post);
                    }
                }
            }
            w.setPosts(posts);
        }
        fileUtils.writeToFile(writers);
    }

    private static Integer getNewId(List<Writer> writers) {
        Optional<Integer> maxId = writers.stream()
                .map(Writer::getId)
                .max(Integer::compare);

        return maxId.orElse(0) + 1;
    }
}
