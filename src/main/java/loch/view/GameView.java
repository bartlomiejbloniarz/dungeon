package loch.view;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import loch.LayoutManager;
import loch.controller.GameController;
import loch.model.GameModel;

public class GameView extends VBox {

    ImageView[][] squares;
    int height, width;
    GameModel model;

    public GameView(GameController controller, GameModel model, ReadOnlyDoubleProperty widthProperty, ReadOnlyDoubleProperty heightProperty){
        super();
        controller.setView(this);
        height = model.getHeight();
        width = model.getWidth();
        this.model = model;
        squares = new ImageView[height][width];
        for (int i=0; i<height; i++){
            for (int j=0; j<width; j++){
                squares[i][j] = LayoutManager.initiateSquare(width, height, widthProperty, heightProperty, model.getBoard()[i][j]);
            }
        }
        squares[model.getHeroPosX()][model.getHeroPosY()] = LayoutManager.initiateSquare(width, height, widthProperty, heightProperty, '@');
        for (int i=0; i<height; i++) {
            HBox tempHBox = new HBox(squares[i]);
            tempHBox.setSpacing(0);
            this.getChildren().add(tempHBox);
        }
        this.addEventHandler(KeyEvent.KEY_RELEASED, controller::handleKeyEvent);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
    }

    public void fixColors(){
        for (GameModel.Pair p: model.getDoors())
            LayoutManager.fixDoor(squares[p.x][p.y], model.isAstral());
        for (GameModel.Pair p: model.getAstralDoors())
            LayoutManager.fixAstralDoor(squares[p.x][p.y], model.isAstral());
        LayoutManager.changeSquareImage(squares[model.getExit().x][model.getExit().y], '>', model.isAstral());
    }

    public void setHeroPos(int heroPosX, int heroPosY){
        LayoutManager.changeSquareImage(squares[model.getHeroPosX()][model.getHeroPosY()], model.getBoard()[model.getHeroPosX()][model.getHeroPosY()], model.isAstral());
        LayoutManager.changeSquareImage(squares[heroPosX][heroPosY], '@', model.isAstral());
    }
}
