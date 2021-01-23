package loch.controller;

import loch.Operator;

public class MainMenuController {

    Operator operator;

    public MainMenuController(Operator operator){
        this.operator = operator;
    }

    public void playButtonClick(){
        operator.showLevelSelectorView();
    }

    public void addButtonClick(){
        operator.showLevelAdderView();
    }

    public void exitButtonClick(){
        operator.close();
    }

    public void settingsButtonClick(){
        operator.showSettingsView();
    }

    public void aboutButtonClick() {
        operator.showAboutView();
    }
}
