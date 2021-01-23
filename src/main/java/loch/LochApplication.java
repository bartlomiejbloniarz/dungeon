package loch;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class LochApplication extends Application {

    HBox root;
    Operator operator;
    Scene scene;

    @Override
    public void start(Stage stage) {

        root = new HBox();
        root.setAlignment(Pos.CENTER);
        scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        root.setBackground(new Background(new BackgroundFill(Color.GREY, null, null)));
        root.prefWidthProperty().bind(stage.widthProperty());
        root.prefHeightProperty().bind(stage.heightProperty());
        operator = new Operator(root, stage);
        stage.setTitle("Sorcerer's dungeon");
        stage.show();

    }

    @Override
    public void stop(){
        operator.save();
    }


    public static void main(String[] args) {
        launch();
    }

}