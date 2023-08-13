package com.voronin.repository.impl;

import com.google.gson.reflect.TypeToken;
import com.voronin.model.Label;
import com.voronin.model.Status;
import com.voronin.repository.LabelRepository;
import com.voronin.repository.PostRepository;
import com.voronin.utils.FileUtils;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Alexey Voronin.
 * @since 09.08.2023.
 */
public class GsonLabelRepositoryImpl implements LabelRepository {

    private final static Path LABELS_FILE = Path.of("src", "main", "resources", "labels.json");
    private final FileUtils<Path, Label> fileUtils = new FileUtils<>(LABELS_FILE, TypeToken.get(Label[].class));
    private final PostRepository postRepository;

    public GsonLabelRepositoryImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Label save(Label label) {
        List<Label> labels = fileUtils.getFromFile();
        int nextId = getNewId(labels);

        label.setId(nextId);
        label.setStatus(Status.ACTIVE);
        labels.add(label);

        fileUtils.writeToFile(labels);
        return label;
    }

    @Override
    public Label findById(Integer id) {
        List<Label> labels = fileUtils.getFromFile();
        Optional<Label> first = labels.stream()
                .filter(label -> label.getId().equals(id))
                .findFirst();

        return first.orElseThrow(() -> new IllegalArgumentException("Не существующий ID: " + id));
    }

    @Override
    public List<Label> findAll() {
        List<Label> labels = fileUtils.getFromFile();
        labels = labels.stream()
                .filter(lab -> lab.getStatus() != Status.DELETED)
                .collect(Collectors.toList());

        return labels;
    }

    @Override
    public Label update(Label label) {
        List<Label> labels = fileUtils.getFromFile();
        Label first = labels.stream()
                .filter(l -> l.getId().equals(label.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Не существующий ID у Label: " + label));

        first.setName(label.getName());
        fileUtils.writeToFile(labels);
        postRepository.updateLabelInPost(first);
        return first;
    }

    @Override
    public void deleteById(Integer id) {
        List<Label> labels = fileUtils.getFromFile();
        Label first = labels.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Не существующий ID: " + id));

        first.setStatus(Status.DELETED);
        fileUtils.writeToFile(labels);
        postRepository.updateLabelInPost(first);
    }

    private static Integer getNewId(List<Label> labels) {
        Optional<Integer> maxId = labels.stream()
                .map(Label::getId)
                .max(Integer::compare);

        return maxId.orElse(0) + 1;
    }
}
