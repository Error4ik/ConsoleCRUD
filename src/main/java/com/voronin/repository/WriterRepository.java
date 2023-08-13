package com.voronin.repository;

import com.voronin.model.Post;
import com.voronin.model.Writer;

/**
 * @author Alexey Voronin.
 * @since 08.08.2023.
 */
public interface WriterRepository extends GenericRepository<Writer, Integer> {
    void addPostToWriter(Writer writer, Post post);

    void removePostFromWriter(Writer writer, Post post);

    void updatePostInWriter(Post post);
}
