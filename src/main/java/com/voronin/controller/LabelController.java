package com.voronin.controller;

import com.voronin.dto.LabelReadDTO;
import com.voronin.mapper.LabelReadMapper;
import com.voronin.model.Label;
import com.voronin.repository.LabelRepository;
import com.voronin.validate.InputValidator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexey Voronin.
 * @since 09.08.2023.
 */
public class LabelController {

    private final LabelRepository labelRepository;
    private final LabelReadMapper labelReadMapper;

    public LabelController(LabelRepository labelRepository, LabelReadMapper labelReadMapper) {
        this.labelRepository = labelRepository;
        this.labelReadMapper = labelReadMapper;
    }

    public LabelReadDTO save(String labelName) {
        InputValidator.stringValidate(labelName);
        return labelReadMapper.map(labelRepository.save(new Label(labelName)));
    }

    public LabelReadDTO findById(String labelId) {
        InputValidator.intValidate(labelId);
        Integer id = Integer.valueOf(labelId);

        return labelReadMapper.map(labelRepository.findById(id));
    }

    public List<LabelReadDTO> findAll() {
        return labelRepository.findAll()
                .stream()
                .map(labelReadMapper::map)
                .collect(Collectors.toList());
    }

    public LabelReadDTO update(String labelId, String labelName) {
        InputValidator.intValidate(labelId);
        InputValidator.stringValidate(labelName);
        Integer id = Integer.valueOf(labelId);

        return labelReadMapper.map(labelRepository.update(new Label(id, labelName)));
    }

    public void delete(String labelId) {
        InputValidator.intValidate(labelId);
        Integer id = Integer.valueOf(labelId);

        labelRepository.deleteById(id);
    }
}
