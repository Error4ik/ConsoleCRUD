package com.voronin.mapper;

import com.voronin.dto.PostReadDTO;
import com.voronin.dto.WriterReadDTO;
import com.voronin.model.Writer;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO: comment.
 *
 * @author Alexey Voronin.
 * @since 10.08.2023.
 */
public class WriterReadMapper implements Mapper<Writer, WriterReadDTO> {

    private final PostReadMapper postReadMapper;

    public WriterReadMapper(PostReadMapper postReadMapper) {
        this.postReadMapper = postReadMapper;
    }

    @Override
    public WriterReadDTO map(Writer writer) {
        WriterReadDTO writerReadDTO = new WriterReadDTO();
        copy(writer, writerReadDTO);
        return writerReadDTO;
    }

    private void copy(Writer writer, WriterReadDTO writerDTO) {
        List<PostReadDTO> posts = writer.getPosts().stream()
                .map(postReadMapper::map)
                .collect(Collectors.toList());

        writerDTO.setId(writer.getId());
        writerDTO.setFirstname(writer.getFirstname());
        writerDTO.setLastname(writer.getLastname());
        writerDTO.setPosts(posts);
    }
}
