package com.small.game.circlegame;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.small.game.circlegame.view.LuckyPanView;


import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends Activity {
    private LuckyPanView mLuckyPanView; //自定义控件对象
    private ImageView mStartBtn;   //指针图标
    private boolean flag = true;   //强制弹框消失之后再一次转转盘
    private RelativeLayout rl;        //全屏可设置点击
    private ImageView iv;
    private ArrayList<String> mStrs = new ArrayList<>(); //你想要的数据源

    private ResultDialog resultDialog; //弹框对象

    private Random random = new Random(); //随机对象
    private boolean isRelease = true;   //判断是否MediaPlayer是否释放的标志
    private int num;  //随机数  决定你能拿到什么就在这
    private MediaPlayer mPlayer = null; // 音频




    //处理弹框 及 关闭音乐
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            try {
                resultDialog = new ResultDialog(MainActivity.this,(String)msg.obj);
                resultDialog.show();
            }catch (Exception e){
                Log.i("cdacscds","cdscdscd");
            }
            resultDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    flag = true;
                }
            });

            mPlayer.release();
            isRelease = true;
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews(); //我们来找控件
        initData(); //初始化数据
        initListener(); //初始化监听
    }

    private void initListener() {
        iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mLuckyPanView.isStart() && flag == true) {
                    flag = false;
                    if (isRelease) {
                        mPlayer = MediaPlayer.create(MainActivity.this, R.raw.fly);
                        mPlayer.start();
                        isRelease = false;
                    }
                    num = random.nextInt(5);
                    mLuckyPanView.luckyStart(num);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(500);
                                mLuckyPanView.luckyEnd();
                                Message message = new Message();
                                if (num == 0) {
                                    message.obj = mStrs.get(mStrs.size() - 1);
                                } else {
                                    message.obj = mStrs.get(num - 1);
                                }

                                handler.sendMessageDelayed(message, 4000);
                                Log.i("dsdscdscds", (String) message.obj);
//                                resultDialog.setText((String) message.obj);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        });
    }

    private void initData() {

        Intent intent =  getIntent();
        for(int i = 0;i < 6;i++){
            mStrs.add( intent.getStringExtra(i+""));
        }
        mLuckyPanView.setmStrs(mStrs);//设置数据源
        Log.i("dsdscdscds", mStrs.toString());
    }

    private void findViews() {
        mLuckyPanView = (LuckyPanView) findViewById(R.id.id_luckypan);
        mStartBtn = (ImageView) findViewById(R.id.iv_choose);
        rl = (RelativeLayout) findViewById(R.id.rl);
        iv = (ImageView)findViewById(R.id.iv_back);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
