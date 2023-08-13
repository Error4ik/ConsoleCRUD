package com.voronin.mapper;

/**
 * TODO: comment.
 *
 * @author Alexey Voronin.
 * @since 09.08.2023.
 */
public interface Mapper<F, T> {

    T map(F object);
}
