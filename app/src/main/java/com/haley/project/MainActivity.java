package com.haley.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.haley.menuview.DDMenuClickListener;
import com.haley.menuview.DDMenuView;
import com.haley.menuview.MenuItemEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DDMenuClickListener {

    DDMenuView menuView;

    List<MenuItemEntity> allEntitys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MenuItemEntity entity;
        for (int i = 0; i < 5; i++) {
            entity = new MenuItemEntity("title" + i, getResources().getDrawable(R.drawable.icon_test));
            allEntitys.add(entity);
        }

        menuView = (DDMenuView) findViewById(R.id.menu);
        menuView.setMenuClickListener(this);
        menuView.setContents(allEntitys);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
//            menuView.open();
        }
    }

    int flag = 0;

    public void click(View view) {

        if (flag != 0 && (flag == (menuView.isOpen() ? 1 : 2))) {
            return;
        }
        flag = menuView.isOpen() ? 1 : 2;
        if (menuView.isOpen()) {
            menuView.close();
        } else {
            menuView.open();
        }
    }

    @Override
    public void menuClick(int index, MenuItemEntity entity) {
        Toast.makeText(this, entity.getMenuText() + " click", Toast.LENGTH_LONG).show();
        flag  = 1;
    }
}
