package loch.controller;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import loch.LayoutManager;
import loch.Operator;
import loch.model.GameModel;
import loch.view.GameView;
import loch.view.GameWindowView;

import java.io.File;

public class GameWindowController {

    Operator operator;
    EventHandler<KeyEvent> moveHandler, endGameHandler;
    GameWindowView view;
    File file;
    GameModel gameModel;

    public GameWindowController(Operator operator){
        this.operator = operator;
    }

    public GameView createGameView(File file, ReadOnlyDoubleProperty widthProperty, ReadOnlyDoubleProperty heightProperty){
        this.file = file;
        gameModel = new GameModel(file);
        GameController gameController = new GameController(gameModel);
        moveHandler = gameController::handleKeyEvent;
        endGameHandler = this::endGame;
        return new GameView(gameController, gameModel, widthProperty, heightProperty);
    }

    public void endGame(KeyEvent keyEvent){
        if (gameModel.isPassed()) {
            operator.setLevelPassed(file.getPath());
            operator.showDialog(":DD", "You won!");
            backButtonClick();
        }
    }

    public void addHandler(){
        view.addKeyPressedHandler(moveHandler);
        view.addKeyPressedHandler(endGameHandler);
    }

    public void backButtonClick(){
        view.removeKeyPressedHandler(moveHandler);
        view.removeKeyPressedHandler(endGameHandler);
        operator.showLevelSelectorView(file.getParent());
    }

    public void setView(GameWindowView view){
        this.view = view;
    }

}
