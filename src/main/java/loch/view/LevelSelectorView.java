package loch.view;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import loch.LayoutManager;
import loch.controller.LevelSelectorController;
import loch.model.LevelSelectorModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LevelSelectorView {

    LevelSelectorController controller;
    List<LayoutManager.Tile> tiles;
    String rootPath = "resources/levels";
    File currentFile, rootFile = new File(rootPath);
    VBox mainVBox;
    HBox root;
    Button backButton;
    LevelSelectorModel model;


    public LevelSelectorView(HBox root, LevelSelectorController controller, LevelSelectorModel model){
        this.root = root;
        this.controller = controller;
        this.model = model;
        controller.setView(this);
        tiles = new ArrayList<>();
        mainVBox = new VBox();
        LayoutManager.manageVBox(mainVBox);
        backButton = new Button("Back");
        LayoutManager.manageButton(root, backButton);
    }

    public void setCurrentFile(String pathname){
        tiles.clear();
        currentFile = new File(pathname);
        for (String name: currentFile.list()) {
            LayoutManager.Tile tempTile = new LayoutManager.Tile(currentFile + "/" + name, name.split("[.]")[0]);
            LayoutManager.manageTile(root, tempTile, model.isPassed(tempTile.getFile().getPath()));
            tempTile.setOnMouseClicked(e -> controller.tileClick(tempTile.getFile()));
            tiles.add(tempTile);
        }
    }

    public void show(){
        show(rootPath);
    }

    public void show(String currentPath){
        setCurrentFile(currentPath);
        mainVBox.getChildren().clear();
        mainVBox.getChildren().addAll(tiles);
        if (!currentFile.equals(rootFile))
            backButton.setOnMouseClicked(e -> controller.backButtonClick(currentFile));
        else
            backButton.setOnMouseClicked(e -> controller.backToMainMenu());
        mainVBox.getChildren().add(backButton);
        root.getChildren().clear();
        root.getChildren().addAll(mainVBox);
    }


}
