package com.voronin.controller;

import com.voronin.dto.WriterReadDTO;
import com.voronin.mapper.WriterReadMapper;
import com.voronin.model.Post;
import com.voronin.model.Writer;
import com.voronin.repository.PostRepository;
import com.voronin.repository.WriterRepository;
import com.voronin.validate.InputValidator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexey Voronin.
 * @since 08.08.2023.
 */
public class WriterController {
    private final WriterRepository writerRepository;

    private final PostRepository postRepository;
    private final WriterReadMapper writerReadMapper;

    public WriterController(WriterRepository writerRepository, PostRepository postRepository, WriterReadMapper writerReadMapper) {
        this.writerRepository = writerRepository;
        this.postRepository = postRepository;
        this.writerReadMapper = writerReadMapper;
    }

    public WriterReadDTO save(String firstName, String lastName) {
        InputValidator.stringValidate(firstName);
        InputValidator.stringValidate(lastName);
        Writer writer = new Writer();
        writer.setFirstname(firstName);
        writer.setLastname(lastName);
        return writerReadMapper.map(writerRepository.save(writer));
    }

    public WriterReadDTO findById(String writerId) {
        InputValidator.intValidate(writerId);
        Integer id = Integer.valueOf(writerId);

        return writerReadMapper.map(writerRepository.findById(id));
    }

    public List<WriterReadDTO> findAll() {
        return writerRepository.findAll()
                .stream()
                .map(writerReadMapper::map)
                .collect(Collectors.toList());
    }

    public WriterReadDTO update(String writerId, String firstName, String lastName) {
        InputValidator.intValidate(writerId);
        Integer id = Integer.valueOf(writerId);
        InputValidator.stringValidate(firstName);
        InputValidator.stringValidate(lastName);

        Writer writer = new Writer();
        writer.setId(id);
        writer.setFirstname(firstName);
        writer.setLastname(lastName);
        return writerReadMapper.map(writerRepository.update(writer));
    }

    public void delete(String writerId) {
        InputValidator.intValidate(writerId);
        Integer id = Integer.valueOf(writerId);

        writerRepository.deleteById(id);
    }

    public void addPostToWriter(String writerId, String postId) {
        InputValidator.intValidate(writerId);
        Integer idWriter = Integer.valueOf(writerId);
        InputValidator.intValidate(postId);
        Integer idPost = Integer.valueOf(postId);

        Writer writer = writerRepository.findById(idWriter);
        Post post = postRepository.findById(idPost);
        writerRepository.addPostToWriter(writer, post);
    }

    public void removePostFromWriter(String writerId, String postId) {
        InputValidator.intValidate(writerId);
        Integer idWriter = Integer.valueOf(writerId);
        InputValidator.intValidate(postId);
        Integer idPost = Integer.valueOf(postId);

        Writer writer = writerRepository.findById(idWriter);
        Post post = postRepository.findById(idPost);
        writerRepository.removePostFromWriter(writer, post);
    }
}
