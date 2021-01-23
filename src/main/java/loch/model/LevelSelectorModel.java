package loch.model;

import java.io.Serializable;
import java.util.HashSet;

public class LevelSelectorModel implements Serializable {
    HashSet<String> passedLevels;

    public LevelSelectorModel(){
        passedLevels = new HashSet<>();
    }

    public boolean isPassed(String path){
        return passedLevels.contains(path);
    }

    public void setPassed(String path){
        passedLevels.add(path);
    }

    public void reset(){
        passedLevels.clear();
    }
}
