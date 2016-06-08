package com.small.game.game;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideEnter.SlideBottomEnter;
import com.flyco.animation.SlideExit.SlideTopExit;
import com.flyco.animation.ZoomExit.ZoomOutBottomExit;
import com.flyco.dialog.widget.MaterialDialog;
import com.flyco.dialog.widget.popup.base.BasePopup;
import com.github.tbouron.shakedetector.library.ShakeDetector;

import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameTouZiActivity extends AppCompatActivity {


    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.iv_lid)
    ImageView ivLid;
    @Bind(R.id.ll_dice)
    LinearLayout llDice;
    @Bind(R.id.tv_dice_num)
    TextView tvDiceNum;
    Context context;
    @Bind(R.id.ll_dice_01)
    LinearLayout llDice01;
    @Bind(R.id.ll_dice_02)
    LinearLayout llDice02;
    @Bind(R.id.ll_dice_more)
    LinearLayout llDiceMore;
    private SimpleCustomPop mQuickCustomPopup;
    MaterialDialog dialog;
    boolean isShock = false;
    int ganmeNum = 6;
    ArrayList<Integer> data = new ArrayList<>();
    Random random = new Random();
    private GestureDetectorCompat gestureDetectorCompat;
    private boolean flagUp = false;
    private boolean flagDown = true;
    private MediaPlayer mediaPlayer = null;
    private MediaPlayer mediaPlayer1 = null;

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            final int FLING_MIN_DISTANCE = 100, FLING_MIN_VELOCITY = 200;
            if (event2.getY() - event1.getY() > FLING_MIN_DISTANCE && Math.abs(velocityY) > FLING_MIN_VELOCITY) {
                // Fling down
                if (flagDown) {
                    flagDown = false;
                    flagUp = true;
                    moveDown();
                }
            } else if (event1.getY() - event2.getY() > FLING_MIN_DISTANCE && Math.abs(velocityY) > FLING_MIN_VELOCITY) {
                // Fling up
                if (flagUp) {
                    flagDown = true;
                    flagUp = false;
                    moveUp();
                }
            }

            return true;
        }
    }

    private void moveUp() {
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(ivLid, "translationY", ivLid.getTranslationY(), -100f);
        animator2.setDuration(300);
        animator2.start();

    }

    private void moveDown() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(ivLid, "translationY", ivLid.getTranslationY(), 400f);
        animator.setDuration(300);
        animator.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_touzi_activity);
        ButterKnife.bind(this);
        context = this;
        gestureDetectorCompat = new GestureDetectorCompat(this, new MyGestureListener());
        //初始化数据 把下面的骰子生成出来
        initData();
        //初始化监听 包括摇一摇 手势监听
        initListener();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private void initListener() {

        ShakeDetector.create(this, new ShakeDetector.OnShakeListener() {
            @Override
            public void OnShake() {
                Toast.makeText(getApplicationContext(), "Device shaken!", Toast.LENGTH_SHORT).show();
                if (!isShock) {
                    isShock = true;
                    letHappy();
                }
            }
        });
    }

    private void initData() {
        data.add(1);
        data.add(2);
        data.add(3);
        data.add(4);
        data.add(5);
        data.add(6);
        mQuickCustomPopup = new SimpleCustomPop(context);
        dialog = new MaterialDialog(GameTouZiActivity.this);
        dialog.title("游戏简介")
                .content("摇动手机,即可晃动骰子,玩家可以在右上角自定义骰子的个数")
                .showAnim(new SlideBottomEnter())
                .dismissAnim(new ZoomOutBottomExit())
                .btnNum(1)
                .show();
        addDice(6);

    }

    public void addDice(int num) {
        if (num <= 3) {
            if (num == 1) {
                ImageView iv1 = new ImageView(context);
                changePic(iv1, data.get(0));
                iv1.setPadding(8, 8, 8, 8);
                llDice.addView(iv1);
            } else if (num == 2) {
                ImageView iv1 = new ImageView(context);
                changePic(iv1, data.get(0));
                ImageView iv2 = new ImageView(context);
                changePic(iv2, data.get(1));
                iv1.setPadding(8, 8, 8, 8);
                iv2.setPadding(8, 8, 8, 8);
                llDice.addView(iv1);
                llDice.addView(iv2);

            } else {
                ImageView iv1 = new ImageView(context);
                changePic(iv1, data.get(0));
                ImageView iv2 = new ImageView(context);
                changePic(iv2, data.get(1));
                ImageView iv3 = new ImageView(context);
                changePic(iv3, data.get(2));
                iv1.setPadding(8, 8, 8, 8);
                iv2.setPadding(8, 8, 8, 8);
                iv3.setPadding(8, 8, 8,8 );
                llDice.addView(iv1);
                llDice.addView(iv2);
                llDice.addView(iv3);
            }

        } else {
            if (num == 4) {
                ImageView iv1 = new ImageView(context);
                changePic(iv1, data.get(0));
                ImageView iv2 = new ImageView(context);
                changePic(iv2, data.get(1));
                ImageView iv3 = new ImageView(context);
                changePic(iv3, data.get(2));
                ImageView iv4 = new ImageView(context);
                changePic(iv4, data.get(3));
                iv1.setPadding(8, 8, 8, 8);
                iv2.setPadding(8, 8, 8, 8);
                iv3.setPadding(8, 8, 8, 8);
                iv4.setPadding(8, 8, 8, 8);


                llDice01.addView(iv1);
                llDice01.addView(iv2);
                llDice02.addView(iv3);
                llDice02.addView(iv4);

            } else if (num == 5) {
                ImageView iv1 = new ImageView(context);
                changePic(iv1, data.get(0));
                ImageView iv2 = new ImageView(context);
                changePic(iv2, data.get(1));
                ImageView iv3 = new ImageView(context);
                changePic(iv3, data.get(2));
                ImageView iv4 = new ImageView(context);
                changePic(iv4, data.get(3));
                ImageView iv5 = new ImageView(context);
                changePic(iv5, data.get(4));
                iv1.setPadding(8, 8, 8, 8);
                iv2.setPadding(8, 8, 8, 8);
                iv3.setPadding(8, 8, 8, 8);
                iv4.setPadding(8, 8, 8, 8);
                iv5.setPadding(8, 8, 8, 8);
                llDice01.addView(iv1);
                llDice01.addView(iv2);
                llDice02.addView(iv3);
                llDice02.addView(iv4);
                llDice02.addView(iv5);

            } else {
                ImageView iv1 = new ImageView(context);
                iv1.setPadding(8, 8, 8, 8);
                changePic(iv1, data.get(0));
                ImageView iv2 = new ImageView(context);
                iv2.setPadding(8, 8, 8, 8);
                changePic(iv2, data.get(1));
                ImageView iv3 = new ImageView(context);
                iv3.setPadding(8, 8, 8, 8);
                changePic(iv3, data.get(2));
                ImageView iv4 = new ImageView(context);
                iv4.setPadding(8, 8, 8, 8);
                changePic(iv4, data.get(3));
                ImageView iv5 = new ImageView(context);
                iv5.setPadding(8, 8, 8, 8);
                changePic(iv5, data.get(4));
                ImageView iv6 = new ImageView(context);
                iv6.setPadding(8, 8, 8, 8);
                changePic(iv6, data.get(5));
                llDice01.addView(iv1);
                llDice01.addView(iv2);
                llDice01.addView(iv3);
                llDice02.addView(iv4);
                llDice02.addView(iv5);
                llDice02.addView(iv6);
            }

        }

    }

    public void changePic(ImageView iv, int num) {
        if (num == 1) {
            iv.setImageResource(R.drawable.game_1);
        } else if (num == 2) {
            iv.setImageResource(R.drawable.game_2);

        } else if (num == 3) {
            iv.setImageResource(R.drawable.game_3);

        } else if (num == 4) {
            iv.setImageResource(R.drawable.game_4);

        } else if (num == 5) {
            iv.setImageResource(R.drawable.game_5);

        } else if (num == 6) {
            iv.setImageResource(R.drawable.game_6);
        }

    }


    public void letHappy() {
        mediaPlayer = MediaPlayer.create(context, R.raw.shake);

        ObjectAnimator animator = ObjectAnimator.ofFloat(ivLid, "translationY", ivLid.getTranslationY(), 400f);

        animator.setDuration(500);

        animator.start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(ivLid, "translationX", ivLid.getTranslationX(), -100f, 100f, ivLid.getTranslationX());
                animator1.setDuration(200);
                animator1.setRepeatCount(5);
                animator1.start();
                animator1.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        showTheAnswer();
                        mediaPlayer1 = MediaPlayer.create(context, R.raw.price);
                        mediaPlayer1.start();
                        ObjectAnimator animator2 = ObjectAnimator.ofFloat(ivLid, "translationY", ivLid.getTranslationY(), -100f);

                        animator2.setDuration(500);

                        animator2.start();
                        animator2.addListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                isShock = false;
                                flagDown = true;
                                flagUp = false;
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        });
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });


            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    private void showTheAnswer() {
        data.clear();
        //产生随机数
        for (int i = 0; i < 6; i++) {
            data.add(random.nextInt(5) + 1);
        }
        llDice.removeAllViews();
        llDice01.removeAllViews();
        llDice02.removeAllViews();
        addDice(ganmeNum);
    }


    @Override
    protected void onResume() {
        super.onResume();
        ShakeDetector.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ShakeDetector.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShakeDetector.destroy();
    }

    @OnClick(R.id.tv_dice_num)
    public void onClick() {
        mQuickCustomPopup
                .anchorView(tvDiceNum)
                .offset(-10, 5)
                .gravity(Gravity.BOTTOM)
                .showAnim(new BounceTopEnter())
                .dismissAnim(new SlideTopExit())
                .dimEnabled(false)
                .show();
    }


    class SimpleCustomPop extends BasePopup<SimpleCustomPop> {

        @Bind(R.id.tv_item_1)
        TextView mTvItem1;
        @Bind(R.id.tv_item_2)
        TextView mTvItem2;
        @Bind(R.id.tv_item_3)
        TextView mTvItem3;
        @Bind(R.id.tv_item_4)
        TextView mTvItem4;
        @Bind(R.id.tv_item_5)
        TextView tvItem5;
        @Bind(R.id.tv_item_6)
        TextView tvItem6;

        public SimpleCustomPop(Context context) {
            super(context);
        }

        @Override
        public View onCreatePopupView() {
            View inflate = View.inflate(mContext, R.layout.game_num_popup, null);
            ButterKnife.bind(this, inflate);
            return inflate;
        }

        @Override
        public void setUiBeforShow() {

        }

        public void changeNum(int num) {
            llDice.removeAllViews();
            llDice01.removeAllViews();
            llDice02.removeAllViews();
            addDice(num);
            ganmeNum = num;
            tvDiceNum.setText(num + "颗");

        }


        @OnClick({R.id.tv_item_1, R.id.tv_item_2, R.id.tv_item_3, R.id.tv_item_4, R.id.tv_item_5, R.id.tv_item_6})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_item_1:
                    ganmeNum = 1;
                    changeNum(1);
                    dismiss();
                    break;
                case R.id.tv_item_2:
                    ganmeNum = 2;
                    changeNum(2);
                    dismiss();
                    break;
                case R.id.tv_item_3:
                    ganmeNum = 3;
                    changeNum(3);
                    dismiss();
                    break;
                case R.id.tv_item_4:
                    ganmeNum = 4;
                    changeNum(4);
                    dismiss();
                    break;
                case R.id.tv_item_5:
                    ganmeNum = 5;
                    changeNum(5);
                    dismiss();
                    break;
                case R.id.tv_item_6:
                    ganmeNum = 6;
                    changeNum(6);
                    dismiss();
                    break;
            }
        }
    }


}
