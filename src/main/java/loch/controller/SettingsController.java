package loch.controller;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import loch.LayoutManager;
import loch.Operator;
import loch.model.SettingsModel;
import loch.view.DialogWindow;

public class SettingsController {

    Operator operator;
    SettingsModel model;

    public SettingsController(Operator operator, SettingsModel model){
        this.operator = operator;
        this.model = model;
    }

    public void resetButtonCLick(){
        Stage stageForDialog = new Stage();
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        yesButton.setOnMouseClicked(e -> {
            operator.reset();
            stageForDialog.close();
        });
        noButton.setOnMouseClicked(e -> stageForDialog.close());
        new DialogWindow(stageForDialog, "Reset", "Are you sure you want to continue?", yesButton, noButton).showAndWait();
    }

    public String textureButtonName(){
        return "Textures: " + model.getTextureType().toString();
    }

    public void texturesButtonClick(){
        if (model.getTextureType() == LayoutManager.TextureType.CLASSIC)
            model.setTextureType(LayoutManager.TextureType.MODERN);
        else
            model.setTextureType(LayoutManager.TextureType.CLASSIC);
        LayoutManager.setTextures(model.getTextureType());
        LayoutManager.reloadTextures();
    }

    public void backButtonCLick(){
        operator.showMainMenu();
    }
}
