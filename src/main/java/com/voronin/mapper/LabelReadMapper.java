package com.voronin.mapper;

import com.voronin.dto.LabelReadDTO;
import com.voronin.model.Label;

/**
 * @author Alexey Voronin.
 * @since 09.08.2023.
 */
public class LabelReadMapper implements Mapper<Label, LabelReadDTO> {

    @Override
    public LabelReadDTO map(Label label) {
        return new LabelReadDTO(label.getId(), label.getName());
    }
}
