package com.voronin.repository;

import com.voronin.model.Label;
import com.voronin.model.Post;

/**
 * @author Alexey Voronin.
 * @since 10.08.2023.
 */
public interface PostRepository extends GenericRepository<Post, Integer> {

    void addLabelToPost(Post post, Label label);

    void removeLabelFromPost(Post post, Label label);

    void updateLabelInPost(Label label);
}
