package loch.controller;

import loch.Operator;
import loch.model.LevelSelectorModel;
import loch.view.LevelSelectorView;

import java.io.File;

public class LevelSelectorController {

    Operator operator;
    LevelSelectorView view;
    LevelSelectorModel model;

    public LevelSelectorController(Operator operator, LevelSelectorModel model){
        this.operator = operator;
        this.model = model;
    }

    public void tileClick(File file){
        if (file.isDirectory()) {
            view.show(file.getPath());
        }
        else
            operator.showGameWindowView(file);
    }

    public void backButtonClick(File file){
        view.show(file.getParent());
    }

    public void backToMainMenu(){
        operator.showMainMenu();
    }

    public void setView(LevelSelectorView view){
        this.view = view;
    }
}
