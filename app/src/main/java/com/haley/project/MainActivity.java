package com.haley.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.haley.menuview.DDMenuView;

public class MainActivity extends AppCompatActivity {

    DDMenuView menuView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuView = (DDMenuView) findViewById(R.id.menu);
        menuView.post(new Runnable() {
            @Override
            public void run() {
                menuView.open();
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
//        menuView.open();
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

}
