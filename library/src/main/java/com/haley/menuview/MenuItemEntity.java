package com.haley.menuview;

import android.graphics.drawable.Drawable;

/**
 * Created by huanglei on 6/29/16.
 */
public class MenuItemEntity {

    private Drawable menuIco;
    private String menuText;

    public MenuItemEntity() {

    }

    public MenuItemEntity(String menuText, Drawable menuIco) {
        this.menuText = menuText;
        this.menuIco = menuIco;
    }


    public Drawable getMenuIco() {
        return menuIco;
    }

    public void setMenuIco(Drawable menuIco) {
        this.menuIco = menuIco;
    }

    public String getMenuText() {
        return menuText;
    }

    public void setMenuText(String menuText) {
        this.menuText = menuText;
    }
}
