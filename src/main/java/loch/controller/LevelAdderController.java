package loch.controller;

import loch.Operator;
import loch.Validator;
import loch.view.LevelAdderView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class LevelAdderController {

    Operator operator;
    LevelAdderView view;
    String path = "resources/levels/CUSTOM";

    public LevelAdderController(Operator operator){
        this.operator = operator;
    }

    public void manageFile(File file){
        try {
            if (Validator.validate(file) == -1)
                operator.showDialog("Error", "Level is impossible to beat");
            else{
                try {
                    Files.copy(file.toPath(), new File(path + '/' + file.getName()).toPath(), StandardCopyOption.COPY_ATTRIBUTES);
                    operator.showDialog("Success", "Level successfully added!");
                } catch (FileAlreadyExistsException e){
                    operator.showDialog("Error", "Level with this name already exists");
                }
            }
        } catch (FileNotFoundException e){
            operator.showDialog("Error", "File not found!"); //impossible
        } catch (IOException e){
            operator.showDialog("Error", "An error occurred while trying to read the file");
        } catch (Validator.FileCorruptedException e){
            operator.showDialog("Error", "File does not represent a valid level or is corrupted");
        } catch (Validator.BoardTooLargeException e){
            operator.showDialog("Error", "The board is too large");
        }
    }

    public void setView(LevelAdderView view){
        this.view = view;
    }
}
