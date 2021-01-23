package loch.controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import loch.model.GameModel;
import loch.view.GameView;

public class GameController {

    GameModel model;
    GameView view;

    public GameController(GameModel model){
        this.model = model;
    }

    public void handleKeyEvent(KeyEvent keyEvent){
        int x = model.getHeroPosX(), y = model.getHeroPosY();
        switch (keyEvent.getCode()){
            case C:
                if (model.getBoard()[x][y] == '%')
                    negateAstral();
                return;
            case UP:
                if (x > 0)
                    x--;
                break;
            case DOWN:
                if (x < model.getHeight() - 1)
                    x++;
                break;
            case RIGHT:
                if (y < model.getWidth() - 1)
                    y++;
                break;
            case LEFT:
                if (y > 0)
                    y--;
                break;
        }
        char move = model.getBoard()[x][y];
        if (move == '.' || move == '>' || move == '%' || (move == '+' && !model.isAstral()) || (move == 'o' && model.isAstral())){
            view.setHeroPos(x, y);
            model.setHeroPosX(x);
            model.setHeroPosY(y);
        }
        if (move == '>' && !model.isAstral())
            model.setPassed(true);
    }

    void negateAstral(){
        model.setAstral(!model.isAstral());
        view.fixColors();
    }

    public void setView(GameView view){
        this.view = view;
    }
}
