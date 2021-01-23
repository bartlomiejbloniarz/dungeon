package loch.view;

import javafx.scene.control.Button;
import javafx.scene.layout.*;
import loch.LayoutManager;
import loch.controller.MainMenuController;

public class MainMenuView {

    HBox root;
    VBox mainVBox;
    Button playButton, addButton, exitButton, settingsButton, aboutButton;
    MainMenuController controller;

    public MainMenuView(HBox root, MainMenuController controller){
        this.root = root;
        this.controller = controller;
        playButton = new Button("Play");
        playButton.setOnMouseClicked(e -> controller.playButtonClick());
        LayoutManager.manageButton(root, playButton);
        addButton = new Button("Add custom level");
        addButton.setOnMouseClicked(e -> controller.addButtonClick());
        LayoutManager.manageButton(root, addButton);
        aboutButton = new Button("About");
        aboutButton.setOnMouseClicked(e -> controller.aboutButtonClick());
        LayoutManager.manageButton(root, aboutButton);
        settingsButton = new Button("Settings");
        settingsButton.setOnMouseClicked(e -> controller.settingsButtonClick());
        LayoutManager.manageButton(root, settingsButton);
        exitButton = new Button("Exit");
        exitButton.setOnMouseClicked(e -> controller.exitButtonClick());
        LayoutManager.manageButton(root, exitButton);
        mainVBox = new VBox(playButton, addButton, aboutButton, settingsButton, exitButton);
        LayoutManager.manageVBox(mainVBox);
    }

    public void show(){
        root.getChildren().clear();
        root.getChildren().add(mainVBox);
    }
}
