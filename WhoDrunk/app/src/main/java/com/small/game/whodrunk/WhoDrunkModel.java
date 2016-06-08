package com.small.game.whodrunk;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by smallstrong on 16/6/3.
 */
public class WhoDrunkModel extends Activity {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_default)
    TextView tvDefault;
    @Bind(R.id.et_01)
    EditText et01;
    @Bind(R.id.et_02)
    EditText et02;
    @Bind(R.id.et_03)
    EditText et03;
    @Bind(R.id.et_04)
    EditText et04;
    @Bind(R.id.et_05)
    EditText et05;
    @Bind(R.id.et_06)
    EditText et06;
    @Bind(R.id.et_07)
    EditText et07;
    @Bind(R.id.et_08)
    EditText et08;

    ArrayList<String> gameDrunkList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.who_drunkgame_model);
        ButterKnife.bind(this);
        initData();
        initListener();
    }

    private void initListener() {
        et01.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeInfo(0,s.toString());
            }
        });
        et02.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeInfo(1,s.toString());
            }
        });
        et03.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeInfo(2,s.toString());
            }
        });
        et04.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeInfo(3,s.toString());
            }
        });
        et05.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeInfo(4,s.toString());
            }
        });
        et06.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeInfo(5,s.toString());
            }
        });
        et07.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeInfo(6,s.toString());
            }
        });
        et08.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeInfo(7,s.toString());
            }
        });
    }

    /**
     * @param index 下标
     * @param str   修改后的字符串
     */

    public void changeInfo(int index, String str) {
        if (!"".endsWith(str.trim())) {
            gameDrunkList.remove(index);
            gameDrunkList.add(index, str);
        }
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
    }

    @OnClick({R.id.iv_back, R.id.tv_default})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                GameDrunkData deliverData = GameDrunkData.getSingleton();
                deliverData.setGameDrunkList(gameDrunkList);
                finish();
                break;
            case R.id.tv_default:
                GameDrunkData deliverData1 = GameDrunkData.getSingleton();
                deliverData1.setGameDrunkList(gameDrunkList);
                finish();
                break;
        }
    }
}
