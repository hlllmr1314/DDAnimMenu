package com.haley.menuview;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huanglei on 6/29/16.
 */
public class DDMenuView extends RelativeLayout {

    View view;

    LinearLayout lay1;
    LinearLayout lay2;
    LinearLayout lay3;
    LinearLayout lay4;
    LinearLayout lay5;
    LinearLayout lay6;

    ImageView ico1;
    ImageView ico2;
    ImageView ico3;
    ImageView ico4;
    ImageView ico5;
    ImageView ico6;

    TextView text1;
    TextView text2;
    TextView text3;
    TextView text4;
    TextView text5;
    TextView text6;

    private List<LinearLayout> views1 = new ArrayList<>();
    private List<LinearLayout> views2 = new ArrayList<>();
    private List<ObjectAnimator> anims1 = new ArrayList<>();
    private List<ObjectAnimator> anims2 = new ArrayList<>();

    private List<MenuItemEntity> menuItemEntities = new ArrayList<>();

    private boolean isOpen = false;

    public DDMenuView(Context context) {
        super(context);
        init();
    }

    public DDMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DDMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DDMenuView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        initView();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void showContent(List<MenuItemEntity> entities) {
        this.menuItemEntities = entities;

    }

    private void init() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(20, 20, 20, 20);
        setLayoutParams(layoutParams);
        setGravity(Gravity.CENTER);
    }

    private void initView() {
        if (view != null) {
            return;
        }
        view = inflate(getContext(), R.layout.menu_view_layout, this);

        lay1 = (LinearLayout) findViewById(R.id.item_lay1);
        lay2 = (LinearLayout) findViewById(R.id.item_lay2);
        lay3 = (LinearLayout) findViewById(R.id.item_lay3);
        lay4 = (LinearLayout) findViewById(R.id.item_lay4);
        lay5 = (LinearLayout) findViewById(R.id.item_lay5);
        lay6 = (LinearLayout) findViewById(R.id.item_lay6);

        views1.clear();
        views2.clear();

        views1.add(lay2);
        views1.add(lay5);

        views2.add(lay1);
        views2.add(lay3);
        views2.add(lay4);
        views2.add(lay6);
    }

    private void showMenuView() {
        lay1.setVisibility(VISIBLE);
        lay2.setVisibility(VISIBLE);
        lay3.setVisibility(VISIBLE);
        lay4.setVisibility(VISIBLE);
        lay5.setVisibility(VISIBLE);
        lay6.setVisibility(VISIBLE);
    }

    private void dismissMenuView() {
        lay1.setVisibility(INVISIBLE);
        lay2.setVisibility(INVISIBLE);
        lay3.setVisibility(INVISIBLE);
        lay4.setVisibility(INVISIBLE);
        lay5.setVisibility(INVISIBLE);
        lay6.setVisibility(INVISIBLE);
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void open() {
        if (isOpen) {
            return;
        }
        for (LinearLayout layout : views1) {
            layout.setVisibility(View.VISIBLE);
            ObjectAnimator objectAnimator = new ObjectAnimator();
            objectAnimator.setTarget(layout);
            objectAnimator.setPropertyName("translationY");
            objectAnimator.setDuration(700);
            objectAnimator.setFloatValues(-layout.getY(), 20, 0);
            objectAnimator.setInterpolator(new DecelerateInterpolator(1));

            objectAnimator.start();
        }

        for (LinearLayout layout : views2) {
            layout.setVisibility(View.VISIBLE);
            ObjectAnimator objectAnimator = new ObjectAnimator();
            objectAnimator.setTarget(layout);
            objectAnimator.setPropertyName("translationY");
            objectAnimator.setDuration(780);
            objectAnimator.setFloatValues(-layout.getY(), 20, 0);
            objectAnimator.setInterpolator(new DecelerateInterpolator(1));

            objectAnimator.start();
        }

        postDelayed(new Runnable() {
            @Override
            public void run() {
                isOpen = true;
            }
        }, 780);
    }

    public void close() {

        if (!isOpen) {
            return;
        }

        anims1.clear();
        anims2.clear();
        for (LinearLayout layout : views1) {
            layout.setVisibility(View.VISIBLE);
            ObjectAnimator objectAnimator = new ObjectAnimator();
            objectAnimator.setTarget(layout);
            objectAnimator.setPropertyName("translationY");
            objectAnimator.setDuration(700);
            objectAnimator.setFloatValues(0, -layout.getY() - layout.getHeight());
            objectAnimator.setInterpolator(new AccelerateInterpolator(1));
            objectAnimator.start();
            anims1.add(objectAnimator);
        }

        for (LinearLayout layout : views2) {
            layout.setVisibility(View.VISIBLE);
            ObjectAnimator objectAnimator = new ObjectAnimator();
            objectAnimator.setTarget(layout);
            objectAnimator.setPropertyName("translationY");
            objectAnimator.setDuration(780);
            objectAnimator.setFloatValues(0, -layout.getY() - layout.getHeight());
            objectAnimator.setInterpolator(new AccelerateInterpolator(1));
            objectAnimator.start();
            anims2.add(objectAnimator);
        }

      postDelayed(new Runnable() {
          @Override
          public void run() {
              dismissMenuView();
              Log.i("1001", views2.get(0).getY() + "dp");
              for (ObjectAnimator animator : anims1) {
                  animator.setDuration(1);
                  animator.reverse();
              }
              for (ObjectAnimator animator : anims2) {
                  animator.setDuration(1);
                  animator.reverse();
              }

              isOpen = false;
          }
      },780);

    }

    public void destory() {
        removeAllViews();
        view = null;
    }

}
