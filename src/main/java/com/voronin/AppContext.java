package com.voronin;

import com.voronin.controller.LabelController;
import com.voronin.controller.PostController;
import com.voronin.controller.WriterController;
import com.voronin.mapper.LabelReadMapper;
import com.voronin.mapper.PostReadMapper;
import com.voronin.mapper.WriterReadMapper;
import com.voronin.repository.LabelRepository;
import com.voronin.repository.PostRepository;
import com.voronin.repository.WriterRepository;
import com.voronin.repository.impl.GsonLabelRepositoryImpl;
import com.voronin.repository.impl.GsonPostRepositoryImpl;
import com.voronin.repository.impl.GsonWriterRepositoryImpl;
import com.voronin.utils.ConsoleInput;
import com.voronin.utils.Input;
import com.voronin.view.LabelView;
import com.voronin.view.PostView;
import com.voronin.view.WriterView;

/**
 * @author Alexey Voronin.
 * @since 17.08.2023.
 */
public class AppContext {
    public static final LabelReadMapper LABEL_READ_MAPPER = new LabelReadMapper();
    public static final PostReadMapper POST_READ_MAPPER = new PostReadMapper(LABEL_READ_MAPPER);
    public static final WriterReadMapper WRITER_READ_MAPPER = new WriterReadMapper(POST_READ_MAPPER);
    public static final Input INPUT = new ConsoleInput();
    public static final WriterRepository WRITER_REPOSITORY = new GsonWriterRepositoryImpl();
    public static final PostRepository POST_REPOSITORY = new GsonPostRepositoryImpl(WRITER_REPOSITORY);
    public static final LabelRepository LABEL_REPOSITORY = new GsonLabelRepositoryImpl(POST_REPOSITORY);
    public static final LabelController LABEL_CONTROLLER = new LabelController(LABEL_REPOSITORY, LABEL_READ_MAPPER);
    public static final LabelView LABEL_VIEW = new LabelView(LABEL_CONTROLLER, INPUT);
    public static final PostController POST_CONTROLLER = new PostController(POST_REPOSITORY, LABEL_REPOSITORY, POST_READ_MAPPER);
    public static final PostView POST_VIEW = new PostView(POST_CONTROLLER, INPUT);
    public static final WriterController WRITER_CONTROLLER = new WriterController(WRITER_REPOSITORY, POST_REPOSITORY, WRITER_READ_MAPPER);
    public static final WriterView WRITER_VIEW = new WriterView(WRITER_CONTROLLER, INPUT);
}
