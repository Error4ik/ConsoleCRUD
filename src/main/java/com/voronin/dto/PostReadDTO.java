package com.voronin.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Alexey Voronin.
 * @since 10.08.2023.
 */
public class PostReadDTO {
    private Integer id;
    private String content;
    private LocalDateTime created;
    private LocalDateTime updated;
    private List<LabelReadDTO> labels;

    public PostReadDTO() {
    }

    public PostReadDTO(Integer id, String content, LocalDateTime created, LocalDateTime updated, List<LabelReadDTO> labels) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.updated = updated;
        this.labels = labels;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public List<LabelReadDTO> getLabels() {
        return labels;
    }

    public void setLabels(List<LabelReadDTO> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "PostReadDTO{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", labels=" + labels +
                '}';
    }
}
