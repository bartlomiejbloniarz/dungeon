package loch.model;

import loch.LayoutManager;

import java.io.Serializable;

public class SettingsModel implements Serializable {
    LayoutManager.TextureType textureType;

    public SettingsModel(){
        textureType = LayoutManager.TextureType.CLASSIC;
    }

    public LayoutManager.TextureType getTextureType(){
        return textureType;
    }

    public void setTextureType(LayoutManager.TextureType textureType) {
        this.textureType = textureType;
    }
}
