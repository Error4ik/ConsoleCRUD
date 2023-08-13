package com.voronin.model;

import java.util.List;
import java.util.Objects;

/**
 * @author Alexey Voronin.
 * @since 08.08.2023.
 */
public class Writer {
    private Integer id;
    private String firstname;
    private String lastname;
    private List<Post> posts;
    private Status status;

    public Writer() {
    }

    public Writer(Integer id, String firstname, String lastname, List<Post> posts, Status status) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.posts = posts;
        this.status = status;
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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Writer writer = (Writer) o;
        return Objects.equals(id, writer.id)
                && Objects.equals(firstname, writer.firstname)
                && Objects.equals(lastname, writer.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }

    @Override
    public String toString() {
        return "Writer{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", posts=" + posts +
                ", status=" + status +
                '}';
    }
}
