package loch;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import loch.controller.*;
import loch.model.LevelSelectorModel;
import loch.model.SettingsModel;
import loch.view.*;

import java.io.*;

public class Operator {

    HBox root;
    Stage stage;
    MainMenuView mainMenuView;
    GameWindowView gameWindowView;
    LevelSelectorView levelSelectorView;
    LevelSelectorModel levelSelectorModel;
    LevelAdderView levelAdderView;
    SettingsView settingsView;
    SettingsModel settingsModel;
    AboutView aboutView;

    public Operator(HBox root, Stage stage){
        this.root = root;
        this.stage = stage;
        createSettingsModel();
        LayoutManager.reloadTextures();
        mainMenuView = new MainMenuView(root, new MainMenuController(this));
        gameWindowView = new GameWindowView(root, new GameWindowController(this));
        createLevelSelectorModel();
        levelSelectorView = new LevelSelectorView(root, new LevelSelectorController(this, levelSelectorModel), levelSelectorModel);
        levelAdderView = new LevelAdderView(root, stage, new LevelAdderController(this));
        aboutView = new AboutView(root, new AboutController(this));
        settingsView = new SettingsView(root, new SettingsController(this, settingsModel));
        showMainMenu();
    }

    public void showMainMenu(){
        mainMenuView.show();
    }

    public void showGameWindowView(File file){
        gameWindowView.show(file);
    }

    public void showLevelSelectorView(){
        levelSelectorView.show();
    }

    public void showLevelSelectorView(String pathname){
        levelSelectorView.show(pathname);
    }

    public void showLevelAdderView(){
        levelAdderView.show();
    }

    public void setLevelPassed(String path){
        levelSelectorModel.setPassed(path);
    }

    public void showSettingsView(){
        settingsView.show();
    }

    public void showDialog(String title, String content){
        new DialogWindow(title, content).showAndWait();
    }

    public void showAboutView(){
        aboutView.show();
    }

    public void reset(){
        levelSelectorModel.reset();
    }

    void createLevelSelectorModel(){
        try{
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("resources/LevelSelectorModel.out"));
            levelSelectorModel = (LevelSelectorModel) objectInputStream.readObject();
        } catch (Exception e) {
            levelSelectorModel = new LevelSelectorModel();
        }
    }

    void createSettingsModel(){
        try{
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("resources/SettingsModel.out"));
            settingsModel = (SettingsModel) objectInputStream.readObject();
        } catch (Exception e) {
            settingsModel = new SettingsModel();
        }
        LayoutManager.setTextures(settingsModel.getTextureType());
    }

    public void save(){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("resources/LevelSelectorModel.out"));
            objectOutputStream.writeObject(levelSelectorModel);
            objectOutputStream = new ObjectOutputStream(new FileOutputStream("resources/SettingsModel.out"));
            objectOutputStream.writeObject(settingsModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        stage.close();
    }

}
