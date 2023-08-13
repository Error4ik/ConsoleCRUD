package com.voronin.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Alexey Voronin.
 * @since 08.08.2023.
 */
public class Label implements Serializable {
    private Integer id;
    private String name;

    private Status status;

    public Label() {
    }

    public Label(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Label(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Label label = (Label) o;
        return Objects.equals(id, label.id) && Objects.equals(name, label.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
