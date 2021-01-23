package loch.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import loch.LayoutManager;
import loch.controller.AboutController;

public class AboutView {
    HBox root;
    VBox mainVBox;
    Button backButton;
    AboutController controller;
    Text text;
    TextFlow textFlow;

    public AboutView(HBox root, AboutController controller){
        this.root = root;
        this.controller = controller;
        backButton = new Button("Back");
        backButton.setOnMouseClicked(e -> controller.backButtonClick());
        LayoutManager.manageButton(root, backButton);
        text = new Text("Hero (@) is running away from sorcerer's dungeon. In one step they can go north, east, west or south. They can't of course step on the wall (#), but they can walk on empty fields (.) and go through the doors (+). If they step on the magical whirlpool field (%) they can, but don't have to, obtain astral form, which allows them to go through astral doors (o), but restrains them from going through standard doors. Magical whirlpool can be also used to come back to material form. To escape the dungeon, the Hero must step on the exit field (>) in material (not astral) form.\n" +
                "\n" +
                "Controls:\n" +
                "Arrows - moving in 4 ways\n" +
                "C - change between material and astral form (works only when the Hero is on the magical whirlpool field)\n" +
                "\n" +
                "New level format:\n" +
                "The first line must contain two numbers (h)eight and (w)idth. Then in each of next h lines there must be w signs representing fields mentioned in the game description. Maximum size is 25x50.");
        text.setFont(new Font(15));
        textFlow = new TextFlow(text);
        textFlow.maxWidthProperty().bind(root.widthProperty().multiply(0.7));
        mainVBox = new VBox(textFlow, backButton);
        LayoutManager.manageVBox(mainVBox);
    }

    public void show(){
        root.getChildren().clear();
        root.getChildren().add(mainVBox);
    }
}
