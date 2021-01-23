package loch.controller;

import loch.Operator;

public class AboutController {
    Operator operator;

    public AboutController(Operator operator){
        this.operator = operator;
    }

    public void backButtonClick(){
        operator.showMainMenu();
    }
}
