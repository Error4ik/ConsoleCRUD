package com.voronin.dto;

/**
 * TODO: comment.
 *
 * @author Alexey Voronin.
 * @since 09.08.2023.
 */
public class LabelReadDTO {

    private Integer id;
    private String name;

    public LabelReadDTO(Integer id, String name) {
        this.id = id;
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

    @Override
    public String toString() {
        return "LabelReadDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
