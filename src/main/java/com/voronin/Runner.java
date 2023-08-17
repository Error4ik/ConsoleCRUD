package com.voronin;

import com.voronin.actions.*;

import java.util.List;


public class Runner {
    public static void main(String[] args) {
        Actions labelActions = new LabelActions(AppContext.INPUT, AppContext.LABEL_VIEW);
        Actions postActions = new PostActions(AppContext.INPUT, AppContext.POST_VIEW);
        Actions writerActions = new WriterActions(AppContext.INPUT, AppContext.WRITER_VIEW);
        List<Actions> actions = List.of(labelActions, postActions, writerActions);
        ActionRunner actionRunner = new ActionRunner(AppContext.INPUT, actions);

        actionRunner.startMenu();
    }
}
