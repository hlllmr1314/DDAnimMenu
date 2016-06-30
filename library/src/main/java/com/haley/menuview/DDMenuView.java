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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huanglei on 6/29/16.
 */
public class DDMenuView extends RelativeLayout {

    private static int ANIM_DURATION = 500;

    View view;

    LinearLayout lay1;
    LinearLayout lay2;
    LinearLayout lay3;
    LinearLayout lay4;
    LinearLayout lay5;
    LinearLayout lay6;

    ImageButton ico1;
    ImageButton ico2;
    ImageButton ico3;
    ImageButton ico4;
    ImageButton ico5;
    ImageButton ico6;

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

    private DDMenuClickListener menuClickListener;

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

    public void setMenuClickListener(DDMenuClickListener menuClickListener) {
        this.menuClickListener = menuClickListener;
    }

    public void setContents(List<MenuItemEntity> entities) {
        if (entities != null) {
            this.menuItemEntities = entities;
        }
        initData();
    }

    private void initData() {

        if (view == null) {
            return;
        }

        views1.clear();
        views2.clear();

        if (menuItemEntities.size() > 0) {

            MenuItemEntity entity;
            for (int i = 0; i < menuItemEntities.size(); i++) {
                entity = menuItemEntities.get(i);
                switch (i) {
                    case 0:
                        ico1.setImageDrawable(entity.getMenuIco());
                        text1.setText(entity.getMenuText());
                        views2.add(lay1);
                        break;
                    case 1:
                        ico2.setImageDrawable(entity.getMenuIco());
                        text2.setText(entity.getMenuText());
                        views2.add(lay2);
                        break;
                    case 2:
                        ico3.setImageDrawable(entity.getMenuIco());
                        text3.setText(entity.getMenuText());
                        views2.add(lay3);
                        break;
                    case 3:
                        ico4.setImageDrawable(entity.getMenuIco());
                        text4.setText(entity.getMenuText());
                        views1.add(lay4);
                        break;
                    case 4:
                        ico5.setImageDrawable(entity.getMenuIco());
                        text5.setText(entity.getMenuText());
                        views1.add(lay5);
                        break;
                    case 5:
                        ico6.setImageDrawable(entity.getMenuIco());
                        text6.setText(entity.getMenuText());
                        views1.add(lay6);
                        break;
                }
            }
        }
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

        ico1 = (ImageButton) findViewById(R.id.item_ico1);
        ico2 = (ImageButton) findViewById(R.id.item_ico2);
        ico3 = (ImageButton) findViewById(R.id.item_ico3);
        ico4 = (ImageButton) findViewById(R.id.item_ico4);
        ico5 = (ImageButton) findViewById(R.id.item_ico5);
        ico6 = (ImageButton) findViewById(R.id.item_ico6);

        text1 = (TextView) findViewById(R.id.item_title1);
        text2 = (TextView) findViewById(R.id.item_title2);
        text3 = (TextView) findViewById(R.id.item_title3);
        text4 = (TextView) findViewById(R.id.item_title4);
        text5 = (TextView) findViewById(R.id.item_title5);
        text6 = (TextView) findViewById(R.id.item_title6);

        ico1.setOnClickListener(listener);
        ico2.setOnClickListener(listener);
        ico3.setOnClickListener(listener);
        ico4.setOnClickListener(listener);
        ico5.setOnClickListener(listener);
        ico6.setOnClickListener(listener);

        initData();

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
            objectAnimator.setDuration(ANIM_DURATION);
            objectAnimator.setFloatValues(-layout.getY(), 20, 0);
            objectAnimator.setInterpolator(new DecelerateInterpolator(1));

            objectAnimator.start();
        }

        for (LinearLayout layout : views2) {
            layout.setVisibility(View.VISIBLE);
            ObjectAnimator objectAnimator = new ObjectAnimator();
            objectAnimator.setTarget(layout);
            objectAnimator.setPropertyName("translationY");
            objectAnimator.setDuration(ANIM_DURATION);
            objectAnimator.setFloatValues(-layout.getY(), 20, 0);
            objectAnimator.setInterpolator(new DecelerateInterpolator(1));

            objectAnimator.start();
        }

        postDelayed(new Runnable() {
            @Override
            public void run() {
                isOpen = true;
            }
        }, ANIM_DURATION);
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
            objectAnimator.setDuration(ANIM_DURATION);
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
            objectAnimator.setDuration(ANIM_DURATION);
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
        }, ANIM_DURATION + 30);

    }

    public void destory() {
        removeAllViews();
        view = null;
    }

    private OnClickListener listener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (menuClickListener != null) {
                if (v == ico1) {
                    menuClickListener.menuClick(0, menuItemEntities.get(0));
                } else if (v == ico2) {
                    menuClickListener.menuClick(1, menuItemEntities.get(1));
                } else if (v == ico3) {
                    menuClickListener.menuClick(2, menuItemEntities.get(2));
                } else if (v == ico4) {
                    menuClickListener.menuClick(3, menuItemEntities.get(3));
                } else if (v == ico5) {
                    menuClickListener.menuClick(4, menuItemEntities.get(4));
                } else if (v == ico6) {
                    menuClickListener.menuClick(5, menuItemEntities.get(5));
                }
            }

            close();
        }
    };

}
