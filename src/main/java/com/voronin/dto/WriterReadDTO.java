package com.voronin.dto;

import java.util.List;

/**
 * TODO: comment.
 *
 * @author Alexey Voronin.
 * @since 10.08.2023.
 */
public class WriterReadDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private List<PostReadDTO> posts;

    public WriterReadDTO() {
    }

    public WriterReadDTO(Integer id, String firstname, String lastname, List<PostReadDTO> posts) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.posts = posts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<PostReadDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostReadDTO> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "WriterReadDTO{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", posts=" + posts +
                '}';
    }
}
