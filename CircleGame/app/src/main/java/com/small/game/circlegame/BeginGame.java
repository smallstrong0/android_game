package com.small.game.circlegame;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.animation.BounceEnter.BounceEnter;
import com.flyco.animation.ZoomExit.ZoomInExit;
import com.flyco.dialog.widget.popup.base.BaseBubblePopup;
import com.flyco.dialog.widget.popup.base.BasePopup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 这个是首页,选模式用的
 * Created by smallstrong on 16/5/23.
 */
public class BeginGame extends Activity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.check_zhenxinhua)
    CheckBox checkZhenxinhua;
    @Bind(R.id.check_damaoxian)
    CheckBox checkDamaoxian;
    @Bind(R.id.ll_choose_model)
    LinearLayout llChooseModel;
    @Bind(R.id.tv_start)
    TextView tvStart;
    @Bind(R.id.tv_model)
    TextView tvModel;
    @Bind(R.id.iv_introduce)
    ImageView ivIntroduce;
    @Bind(R.id.iv_bg)
    ImageView ivBg;

    Context context;//上下文
    ArrayList<Boolean> checkList = new ArrayList<>();//选真心话还是大冒险
    PopupWindow mPopupWindow;//选择模式出现的弹窗

    ArrayList<String> dataZhen = new ArrayList<>();//真心话集合
    ArrayList<String> dataDa = new ArrayList<>();//大冒险集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_introduce);
        ButterKnife.bind(this);
        context = this;
        initData();//初始化数据
        initListener();//初始化监听

    }

    private void initData() {
        //首先把popupwindow准备好
        View popupView = getLayoutInflater().inflate(R.layout.popup_custom, null);
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


        //把默认数据准备好吧
        checkList.add(true);
        checkList.add(false);
        dataZhen.add("上次ML选择了什么避孕方式");
        dataZhen.add("说出三种避孕方式");
        dataZhen.add("挑一名同性亲吻脸颊十秒");
        dataZhen.add("选择一位异性嘴对嘴传递食物");
        dataZhen.add("和最爱的人一起睡你会做什么");
        dataZhen.add("身上最敏感的部位");
        dataZhen.add("怎么看待性");

        dataDa.add("[女]例假什么时候来?[男]持续力多久?");
        dataDa.add("第一次是什么时候");
        dataDa.add("与右边异性十指相扣对视10秒");
        dataDa.add("第一个喜欢的异性是什么名字");
        dataDa.add("向左边隔壁桌的女女求拥抱");
        dataDa.add("现在想被有钱人包养吗");
        dataDa.add("当过第三者吗");
    }

    /**
     * 弹窗事件
     * @param popupView
     */
    private void setClickListener(View popupView) {
        popupView.findViewById(R.id.tv_item_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeNum("正常模式");
                mPopupWindow.dismiss();
            }
        });
        popupView.findViewById(R.id.tv_item_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeNum("疯狂模式");
                mPopupWindow.dismiss();
            }
        });
        popupView.findViewById(R.id.tv_item_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeNum("自定义模式");
                startActivity(new Intent(context, ChooseModel.class));
                mPopupWindow.dismiss();
            }
        });
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        checkZhenxinhua.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkList.remove(0);
                checkList.add(0, isChecked);
                Log.i("cdnsjicdsi", checkList.toString());
            }
        });
        checkDamaoxian.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkList.remove(1);
                checkList.add(1, isChecked);
                Log.i("cdnsjicdsi", checkList.toString());
            }
        });
        ivIntroduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivIntroduce.setVisibility(View.GONE);
                ivBg.setVisibility(View.GONE);
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        llChooseModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.showAsDropDown(llChooseModel);
            }
        });
        tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BeginGame.this, MainActivity.class);

                int[] key = randomCommon(0, dataDa.size() + dataZhen.size() - 1, 6);

                for (int i = 0; i < key.length; i++) {
                    if (key[i] <= 6) {
                        intent.putExtra(i + "", dataZhen.get(key[i]));
                    } else {
                        intent.putExtra(i + "", dataDa.get(key[i] - dataZhen.size()));
                    }
                }
                startActivity(intent);
            }
        });

    }


    /**
     * 随机指定范围内N个不重复的数
     * 最简单最基本的方法
     *
     * @param min 指定范围最小值
     * @param max 指定范围最大值
     * @param n   随机数个数
     */
    public static int[] randomCommon(int min, int max, int n) {
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while (count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (num == result[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result[count] = num;
                count++;
            }
        }
        return result;
    }



    /**
     * 利用单利模式传值
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (DeliverData.getSingleton().getDataZhen().size() != 0) {
            dataDa.clear();
            dataZhen.clear();
            dataDa.addAll(DeliverData.getSingleton().getDataDa());
            dataZhen.addAll(DeliverData.getSingleton().getDataZhen());
        }

        Log.i("cdscdscdscdsc", dataDa.toString() + dataZhen.toString());
    }

    /**
     * 选择模式的时候改变模式内容
     * @param str
     */
    public void changeNum(String str) {
        tvModel.setText(str);
    }


}
