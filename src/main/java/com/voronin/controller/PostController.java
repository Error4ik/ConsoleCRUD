package com.voronin.controller;

import com.voronin.dto.PostReadDTO;
import com.voronin.mapper.PostReadMapper;
import com.voronin.model.Label;
import com.voronin.model.Post;
import com.voronin.repository.LabelRepository;
import com.voronin.repository.PostRepository;
import com.voronin.validate.InputValidator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexey Voronin.
 * @since 10.08.2023.
 */
public class PostController {
    private final PostRepository postRepository;
    private final LabelRepository labelRepository;
    private final PostReadMapper postReadMapper;

    public PostController(PostRepository postRepository, LabelRepository labelRepository, PostReadMapper postReadMapper) {
        this.postRepository = postRepository;
        this.labelRepository = labelRepository;
        this.postReadMapper = postReadMapper;
    }

    public PostReadDTO save(String postContent) {
        InputValidator.stringValidate(postContent);
        Post post = new Post();
        post.setContent(postContent);
        postRepository.save(post);
        return postReadMapper.map(post);
    }

    public PostReadDTO findById(String postId) {
        InputValidator.intValidate(postId);
        Integer id = Integer.valueOf(postId);

        return postReadMapper.map(postRepository.findById(id));
    }

    public List<PostReadDTO> findAll() {
        return postRepository.findAll()
                .stream()
                .map(postReadMapper::map)
                .collect(Collectors.toList());
    }

    public PostReadDTO update(String postId, String postContent) {
        InputValidator.intValidate(postId);
        Integer id = Integer.valueOf(postId);

        Post post = new Post();
        post.setId(id);
        post.setContent(postContent);
        return postReadMapper.map(postRepository.update(post));
    }

    public void delete(String postId) {
        InputValidator.intValidate(postId);
        Integer id = Integer.valueOf(postId);

        postRepository.deleteById(id);
    }

    public void addLabelToPost(String postId, String labelId) {
        InputValidator.intValidate(postId);
        Integer idPost = Integer.valueOf(postId);

        InputValidator.intValidate(labelId);
        Integer idLabel = Integer.valueOf(labelId);

        Post post = postRepository.findById(idPost);
        Label label = labelRepository.findById(idLabel);
        postRepository.addLabelToPost(post, label);
    }

    public void removeLabelFromPost(String postId, String labelId) {
        InputValidator.intValidate(postId);
        Integer idPost = Integer.valueOf(postId);

        InputValidator.intValidate(labelId);
        Integer idLabel = Integer.valueOf(labelId);

        Post post = postRepository.findById(idPost);
        Label label = labelRepository.findById(idLabel);
        postRepository.removeLabelFromPost(post, label);
    }
}
