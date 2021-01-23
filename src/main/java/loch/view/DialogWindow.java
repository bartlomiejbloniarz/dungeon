package loch.view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import loch.LayoutManager;

public class DialogWindow {

    Stage stage;
    Scene scene;
    VBox mainVBox;
    Label label;

    public DialogWindow(Stage stage, String title, String content, Button... buttons){
        this.stage = stage;
        stage.setTitle(title);
        label = new Label(content);
        HBox buttonsHBox = new HBox();
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setSpacing(5);
        for (Button b: buttons) {
            LayoutManager.manageSmallButton(b);
            buttonsHBox.getChildren().add(b);
        }
        mainVBox = new VBox(label, buttonsHBox);
        mainVBox.setBackground(new Background(new BackgroundFill(Color.GREY, null, null)));
        LayoutManager.manageVBox(mainVBox);
        scene = new Scene(mainVBox, 300, 100);
        stage.setScene(scene);
    }

    public DialogWindow(String title, String content){
        stage = new Stage();
        stage.setTitle(title);
        Button okButton = new Button("OK");
        okButton.setOnMouseClicked(e -> stage.close());
        label = new Label(content);
        mainVBox = new VBox(label, okButton);
        mainVBox.setBackground(new Background(new BackgroundFill(Color.GREY, null, null)));
        scene = new Scene(mainVBox, 300, 100);
        LayoutManager.manageSmallButton(okButton);
        LayoutManager.manageVBox(mainVBox);
        stage.setScene(scene);
    }

    public void showAndWait(){
        stage.showAndWait();
    }
}
