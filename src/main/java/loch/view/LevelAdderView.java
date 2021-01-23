package loch.view;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import loch.controller.LevelAdderController;

import java.io.File;

public class LevelAdderView {

    HBox root;
    Stage stage;
    LevelAdderController controller;
    FileChooser fileChooser;

    public LevelAdderView(HBox root, Stage stage, LevelAdderController controller){
        this.root = root;
        this.stage = stage;
        this.controller = controller;
        controller.setView(this);
        fileChooser = new FileChooser();
        fileChooser.setTitle("Select file");
    }

    public void show(){
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null)
            controller.manageFile(selectedFile);
    }
}
