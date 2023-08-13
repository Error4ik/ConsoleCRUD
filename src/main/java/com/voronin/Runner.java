package com.voronin;

import com.voronin.actions.*;
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

import java.util.List;


public class Runner {
    private static final LabelReadMapper labelReadMapper = new LabelReadMapper();
    private static final PostReadMapper postReadMapper = new PostReadMapper(labelReadMapper);
    private static final WriterReadMapper writerReadMapper = new WriterReadMapper(postReadMapper);
    private static final Input input = new ConsoleInput();
    private static final WriterRepository writerRepository = new GsonWriterRepositoryImpl();
    private static final PostRepository postRepository = new GsonPostRepositoryImpl(writerRepository);
    private static final LabelRepository labelRepository = new GsonLabelRepositoryImpl(postRepository);
    private static final LabelController labelController = new LabelController(labelRepository, labelReadMapper);
    private static final LabelView labelView = new LabelView(labelController, input);
    private static final PostController postController = new PostController(postRepository, labelRepository, postReadMapper);
    private static final PostView postView = new PostView(postController, input);
    private static final WriterController writerController = new WriterController(writerRepository, postRepository, writerReadMapper);
    private static final WriterView writerView = new WriterView(writerController, input);

    public static void main(String[] args) {
        Actions labelActions = new LabelActions(input, labelView);
        Actions postActions = new PostActions(input, postView);
        Actions writerActions = new WriterActions(input, writerView);
        List<Actions> actions = List.of(labelActions, postActions, writerActions);
        ActionRunner actionRunner = new ActionRunner(input, actions);

        actionRunner.startMenu();
    }
}
