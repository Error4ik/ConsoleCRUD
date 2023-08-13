package com.voronin.mapper;

import com.voronin.dto.LabelReadDTO;
import com.voronin.dto.PostReadDTO;
import com.voronin.model.Post;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexey Voronin.
 * @since 10.08.2023.
 */
public class PostReadMapper implements Mapper<Post, PostReadDTO> {

    private final LabelReadMapper labelReadMapper;

    public PostReadMapper(LabelReadMapper labelReadMapper) {
        this.labelReadMapper = labelReadMapper;
    }

    @Override
    public PostReadDTO map(Post post) {
        List<LabelReadDTO> labels = post.getLabels().stream()
                .map(labelReadMapper::map)
                .collect(Collectors.toList());

        PostReadDTO postReadDTO = new PostReadDTO();
        postReadDTO.setId(post.getId());
        postReadDTO.setContent(post.getContent());
        postReadDTO.setCreated(post.getCreated());
        postReadDTO.setUpdated(post.getUpdated());
        postReadDTO.setLabels(labels);

        return postReadDTO;
    }
}
