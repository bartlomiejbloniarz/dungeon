package loch.view;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import loch.LayoutManager;
import loch.controller.SettingsController;

public class SettingsView {

    HBox root;
    VBox mainVBox;
    Button resetButton, backButton, texturesButton;
    SettingsController controller;

    public SettingsView(HBox root, SettingsController controller){
        this.root = root;
        this.controller = controller;
        resetButton = new Button("Reset");
        resetButton.setOnMouseClicked(e -> controller.resetButtonCLick());
        LayoutManager.manageButton(root, resetButton);
        backButton = new Button("Back");
        backButton.setOnMouseClicked(e -> controller.backButtonCLick());
        LayoutManager.manageButton(root, backButton);
        texturesButton = new Button(controller.textureButtonName());
        texturesButton.setOnMouseClicked(e -> {
            controller.texturesButtonClick();
            texturesButton.setText(controller.textureButtonName());
        });
        LayoutManager.manageButton(root, texturesButton);
        mainVBox = new VBox(resetButton, texturesButton, backButton);
        LayoutManager.manageVBox(mainVBox);
    }

    public void show(){
        root.getChildren().clear();
        root.getChildren().add(mainVBox);
    }

}
