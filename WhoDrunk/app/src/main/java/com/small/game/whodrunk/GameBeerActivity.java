package com.small.game.whodrunk;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameBeerActivity extends AppCompatActivity {

    @Bind(R.id.glass)
    WineGlass glass;
    @Bind(R.id.iv)
    ImageView iv;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.iv_menu)
    ImageView ivMenu;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.iv_introduce)
    ImageView ivIntroduce;
    @Bind(R.id.tv_ignore)
    TextView tvIgnore;
    @Bind(R.id.iv_bg)
    ImageView ivBg;
    private int count = 8;
    PopupWindow mPopupWindow;//选择模式出现的弹窗
    PopupWindow mPopupWindowBottom;//选择模式出现的弹窗
    private ResultDialog resultDialog; //弹框对象
    private Random random = new Random(); //随机对象

    private MediaPlayer mPlayer = null; // 音频


    //处理弹框 及 关闭音乐
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            try {
                resultDialog = new ResultDialog(GameBeerActivity.this, (String) msg.obj);
                resultDialog.show();
            } catch (Exception e) {
                Log.i("cdacscds", "cdscdscd");
            }
            resultDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    flag = true;
                }
            });
            super.handleMessage(msg);
        }
    };
    private boolean see = true;
    private MediaPlayer mPlayer1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_beer_main);
        ButterKnife.bind(this);
        initData();
        initListener();
    }

    private void initListener() {
        tvIgnore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivIntroduce.setVisibility(View.GONE);
                ivBg.setVisibility(View.GONE);
                tvIgnore.setVisibility(View.GONE);
                SharedPreferences sharedPreferences = getSharedPreferences("ignore", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("see_drunk_game",false);
                editor.commit();
            }
        });

        ivIntroduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivIntroduce.setVisibility(View.GONE);
                ivBg.setVisibility(View.GONE);
                tvIgnore.setVisibility(View.GONE);
            }
        });


    }

    private void initData() {
        gameDrunkList.add("喝一杯");
        gameDrunkList.add("PASS");
        gameDrunkList.add("找人喝");
        gameDrunkList.add("PASS");
        gameDrunkList.add("吹瓶子");
        gameDrunkList.add("交杯酒");
        gameDrunkList.add("边跳舞边喝酒");
        gameDrunkList.add("上家喝");


        SharedPreferences sharedPreferences = getSharedPreferences("ignore", MODE_PRIVATE);
        see = sharedPreferences.getBoolean("see_drunk_game", true);
        if(!see){
            ivIntroduce.setVisibility(View.GONE);
            ivBg.setVisibility(View.GONE);
            tvIgnore.setVisibility(View.GONE);
        }

        View popupView = getLayoutInflater().inflate(R.layout.popup_game_wine, null);
        setClickListener(popupView);//这里吧弹窗的一些点击事件抽出去了
        mPopupWindow = new PopupWindow(popupView, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));

        mPopupWindow.getContentView().setFocusableInTouchMode(true);
        mPopupWindow.getContentView().setFocusable(true);
        mPopupWindow.getContentView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (mPopupWindow != null && mPopupWindow.isShowing()) {
                        mPopupWindow.dismiss();
                    }
                    return true;
                }
                return false;
            }
        });


        View popupViewBottom = getLayoutInflater().inflate(R.layout.popup_game_bottom, null);
        setClickListenerBottom(popupViewBottom);//这里吧弹窗的一些点击事件抽出去了
        mPopupWindowBottom = new PopupWindow(popupViewBottom, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindowBottom.setTouchable(true);
        mPopupWindowBottom.setOutsideTouchable(true);
        mPopupWindowBottom.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));

        mPopupWindowBottom.getContentView().setFocusableInTouchMode(true);
        mPopupWindowBottom.getContentView().setFocusable(true);
        mPopupWindowBottom.getContentView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (mPopupWindowBottom != null && mPopupWindowBottom.isShowing()) {
                        mPopupWindowBottom.dismiss();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    private void setClickListenerBottom(View popupViewBottom) {

        popupViewBottom.findViewById(R.id.tv_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindowBottom.dismiss();
                glass.setCount(count);
            }
        });

        final SeekBar pb = (SeekBar) popupViewBottom.findViewById(R.id.pb);
        final TextView tvNum = (TextView) popupViewBottom.findViewById(R.id.tv_num);
        pb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("cdscsdcdscds", progress + "");
                tvNum.setText(progress + "个");
                if(progress == 0){
                    progress = 1;
                }
                count = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    /**
     * 弹窗事件
     *
     * @param popupView
     */
    private void setClickListener(View popupView) {
        popupView.findViewById(R.id.tv_item_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                mPopupWindowBottom.showAtLocation(rl, Gravity.BOTTOM, 0, 0);
            }
        });
        popupView.findViewById(R.id.tv_item_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                startActivity(new Intent(GameBeerActivity.this, WhoDrunkModel.class));
            }
        });

    }

    int num = 0;

    @OnClick({R.id.iv_back, R.id.iv_menu, R.id.rl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_menu:
                mPopupWindow.showAsDropDown(ivMenu);
                break;
            case R.id.rl:
                num = random.nextInt(count);
                startAnim(360 / count * num);
                break;
        }
    }

    ArrayList<String> gameDrunkList = new ArrayList<>();

    /**
     * 利用单利模式传值
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (GameDrunkData.getSingleton().getGameDrunkList().size() != 0) {
            gameDrunkList.clear();
            gameDrunkList.addAll(GameDrunkData.getSingleton().getGameDrunkList());
        }
    }

    boolean flag = true;


    private void startAnim(float x) {
        if (flag) {
            playMusic();
            ObjectAnimator animator = ObjectAnimator.ofFloat(iv, "rotation", 0f, 7200f + x);
            animator.setDuration(4000);
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    flag = false;
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mPlayer.stop();
                    mPlayer.release();
                    playRing();
                    Message message = Message.obtain();
                    message.obj = gameDrunkList.get(random.nextInt(gameDrunkList.size()));
                    handler.sendMessageDelayed(message, 50);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });

            animator.start();
        }
    }

    private void playMusic() {
        mPlayer = MediaPlayer.create(this, R.raw.beer);
        mPlayer.start();
    }
    private void playRing() {
        mPlayer1 = MediaPlayer.create(this, R.raw.ring);
        mPlayer1.start();
    }
}
