package loch;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LayoutManager {

    static String buttonPath = "resources/layout/button9.png", directoryTilePath = "resources/layout/button8.png", notPassedTilePath = "resources/layout/button7.png",
            passedTilePath = "resources/layout/button6.png", smallButtonPath = "resources/layout/smallButton.png";
    static Image buttonImage, directoryTileImage, notPassedTileImage, passedTileImage, smallButtonImage;

    public enum TextureType{ CLASSIC, MODERN }

    static final String classicTextures = "resources/layout/classicTextures/";
    static final String modernTextures = "resources/layout/modernTextures/";

    static String currentTextures = classicTextures;

    static Image corridorImage, wallImage, heroImage, whirlpoolImage, greenDoorImage, redDoorImage, greenAstralDoorImage,
            redAstralDoorImage, greenExitImage, redExitImage;

    static BackgroundSize backgroundSizeCover = new BackgroundSize(0, 0, false, false, false, true);

    static {
        try {
            buttonImage = new Image(new FileInputStream(buttonPath), 1000, 200, false, true);
            directoryTileImage = new Image(new FileInputStream(directoryTilePath), 1000, 200, false, true);
            notPassedTileImage = new Image(new FileInputStream(notPassedTilePath), 1000, 200, false, true);
            passedTileImage = new Image(new FileInputStream(passedTilePath), 1000, 200, false, true);
            smallButtonImage = new Image(new FileInputStream(smallButtonPath), 500, 200, false, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setTextures(TextureType textureType){
        if (textureType == TextureType.CLASSIC)
            currentTextures = classicTextures;
        else
            currentTextures = modernTextures;
    }

    public static void reloadTextures(){
        try {
            corridorImage = new Image(new FileInputStream(currentTextures + "corridor.png"), 500, 500, false, true);
            wallImage = new Image(new FileInputStream(currentTextures + "wall.png"), 500, 500, false, true);
            heroImage = new Image(new FileInputStream(currentTextures + "hero.png"), 500, 500, false, false);
            whirlpoolImage = new Image(new FileInputStream(currentTextures + "whirlpool.png"), 500, 500, false, true);
            greenDoorImage = new Image(new FileInputStream(currentTextures + "greenDoor.png"), 500, 500, false, true);
            redDoorImage = new Image(new FileInputStream(currentTextures + "redDoor.png"), 500, 500, false, true);
            greenAstralDoorImage = new Image(new FileInputStream(currentTextures + "greenAstralDoor.png"), 500, 500, false, true);
            redAstralDoorImage = new Image(new FileInputStream(currentTextures + "redAstralDoor.png"), 500, 500, false, true);
            greenExitImage = new Image(new FileInputStream(currentTextures + "greenExit.png"), 500, 500, false, true);
            redExitImage = new Image(new FileInputStream(currentTextures + "redExit.png"), 500, 500, false, true);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    static void bindButton(Region root, Button button){
        button.prefHeightProperty().bind(root.heightProperty().divide(10));
        button.prefWidthProperty().bind(button.heightProperty().multiply(5));
    }

    public static void setDefaultBackgroundForButton(Button button){
        setBackgroundForButton(button, buttonImage);
    }

    static void setBackgroundForButton(Button button, Image image){
        button.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSizeCover)));
    }

    public static void manageSmallButton(Button button){
        setBackgroundForButton(button, smallButtonImage);
        button.setTextFill(Color.WHITE);
        button.setMaxSize(100, 40);
        button.setPrefSize(100, 40);
    }

    public static void manageButton(Region root, Button button){
        setBackgroundForButton(button, buttonImage);
        button.setTextFill(Color.WHITE);
        bindButton(root, button);
    }

    public static void manageVBox(VBox vBox){
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER);
    }

    static void setBackgroundForTile(Tile tile, boolean isPassed){
        if (tile.getFile().isDirectory())
            setBackgroundForButton(tile, directoryTileImage);
        else{
            if (isPassed)
                setBackgroundForButton(tile, passedTileImage);
            else
                setBackgroundForButton(tile, notPassedTileImage);
        }
    }

    public static void manageTile(Region root, Tile tile, boolean isPassed){
        setBackgroundForTile(tile, isPassed);
        bindButton(root, tile);
    }

    public static void fixDoor(ImageView square, boolean isAstral){
        Image tempImage;
        if (isAstral)
            tempImage = redDoorImage;
        else
            tempImage = greenDoorImage;
        square.setImage(tempImage);
    }

    public static void fixAstralDoor(ImageView square, boolean isAstral){
        Image tempImage;
        if (isAstral)
            tempImage = greenAstralDoorImage;
        else
            tempImage = redAstralDoorImage;
        square.setImage(tempImage);
    }

    public static void changeSquareImage(ImageView square, char c, boolean isAstral){
        Image tempImage;
        if (c == '@')
            tempImage = heroImage;
        else if (c == '#')
            tempImage = wallImage;
        else if (c == '.')
            tempImage = corridorImage;
        else if (c == '%')
            tempImage = whirlpoolImage;
        else if (c == '>'){
            if (isAstral)
                tempImage = redExitImage;
            else
                tempImage = greenExitImage;
        }
        else if (c == '+') {
            if (isAstral)
                tempImage = redDoorImage;
            else
                tempImage = greenDoorImage;
        }
        else {
            if (isAstral)
                tempImage = greenAstralDoorImage;
            else
                tempImage = redAstralDoorImage;
        }
        square.setImage(tempImage);
    }

    public static ImageView initiateSquare(int width, int height, ReadOnlyDoubleProperty widthProperty, ReadOnlyDoubleProperty heightProperty, char c){
        ImageView tempImageView = new ImageView();
        changeSquareImage(tempImageView, c, false);
        tempImageView.setPreserveRatio(true);
        IntegerProperty integerProperty = new SimpleIntegerProperty();
        integerProperty.bind(Bindings.min(widthProperty.multiply(0.8).divide(width), heightProperty.multiply(0.8).divide(height)));
        tempImageView.fitWidthProperty().bind(integerProperty);
        return tempImageView;
    }

    public static class Tile extends Button {

        File file;

        public Tile(String path, String name){
            super(name);
            file = new File(path);
        }

        public File getFile(){
            return file;
        }
    }

    public static void manageGameView(Region root, VBox gameView){
        gameView.setSpacing(0);
        //gameView.prefWidthProperty().bind(root.widthProperty().multiply(0.8));
    }

}
