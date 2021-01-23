package loch.view;

import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import loch.LayoutManager;
import loch.controller.GameWindowController;

import java.io.File;

public class GameWindowView {

    HBox root;
    VBox mainVBox;
    Button backButton;
    GameView gameView;
    GameWindowController controller;

    public GameWindowView(HBox root, GameWindowController controller){
        this.root = root;
        this.controller = controller;
        controller.setView(this);
        backButton = new Button("Back");
        LayoutManager.manageButton(root, backButton);
        mainVBox = new VBox();
        LayoutManager.manageVBox(mainVBox);
    }

    public void show(File file){
        gameView = controller.createGameView(file, root.widthProperty(), root.heightProperty());
        LayoutManager.manageGameView(root, gameView);
        mainVBox.getChildren().clear();
        mainVBox.getChildren().addAll(gameView, backButton);
        root.getChildren().clear();
        root.getChildren().add(mainVBox);
        controller.addHandler();
        backButton.setOnMouseClicked(e -> controller.backButtonClick());
    }

    public void addKeyPressedHandler(EventHandler<KeyEvent> eventHandler){
        root.addEventHandler(KeyEvent.KEY_RELEASED, eventHandler);
    }

    public void removeKeyPressedHandler(EventHandler<KeyEvent> eventHandler){
        root.removeEventHandler(KeyEvent.KEY_RELEASED, eventHandler);
    }

    public void manageGameView(GameView gameView){
        LayoutManager.manageGameView(root, gameView);
    }

}
