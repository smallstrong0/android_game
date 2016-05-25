package com.small.game.circlegame;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 这个类就是修改真心话大冒险 自己定义内容
 * Created by smallstrong on 16/5/23.
 */
public class ChooseModel extends Activity {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_default)
    TextView tvDefault;
    @Bind(R.id.et_zhenxin_01)
    EditText etZhenxin01;
    @Bind(R.id.et_zhenxin_02)
    EditText etZhenxin02;
    @Bind(R.id.et_zhenxin_03)
    EditText etZhenxin03;
    @Bind(R.id.et_zhenxin_04)
    EditText etZhenxin04;
    @Bind(R.id.et_zhenxin_05)
    EditText etZhenxin05;
    @Bind(R.id.et_zhenxin_06)
    EditText etZhenxin06;
    @Bind(R.id.et_zhenxin_07)
    EditText etZhenxin07;
    @Bind(R.id.et_damao_01)
    EditText etDamao01;
    @Bind(R.id.et_damao_02)
    EditText etDamao02;
    @Bind(R.id.et_damao_03)
    EditText etDamao03;
    @Bind(R.id.et_damao_04)
    EditText etDamao04;
    @Bind(R.id.et_damao_05)
    EditText etDamao05;
    @Bind(R.id.et_damao_06)
    EditText etDamao06;
    @Bind(R.id.et_damao_07)
    EditText etDamao07;

    ArrayList<String> dataZhen = new ArrayList<>();
    ArrayList<String> dataDa = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_model);
        ButterKnife.bind(this);
        initData();
        initListener();
    }
    private void initData() {
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
     *
     * @param index 下标
     * @param str    修改后的字符串
     * @param model  模式 真心话还是大冒险
     */

    public void changeInfo(int index ,String str , int model){
        if (model == 0){
            if(!"".endsWith(str.trim()) ){
                dataZhen.remove(index);
                dataZhen.add(index,str);
            }
        }else {
            if(!"".endsWith(str.trim())){
                dataDa.remove(index);
                dataDa.add(index,str);
            }
        }

    }

    private void initListener() {
        etZhenxin01.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeInfo(0,s.toString(),0);
            }
        });
        etZhenxin02.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeInfo(1,s.toString(),0);
            }
        });
        etZhenxin03.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeInfo(2,s.toString(),0);
            }
        });
        etZhenxin04.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeInfo(3,s.toString(),0);
            }
        });
        etZhenxin05.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeInfo(4,s.toString(),0);
            }
        });
        etZhenxin06.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeInfo(5,s.toString(),0);
            }
        });
        etZhenxin07.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeInfo(6,s.toString(),0);
            }
        });
        etDamao01.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeInfo(0,s.toString(),1);
            }
        });
        etDamao02.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeInfo(1,s.toString(),1);
            }
        });
        etDamao03.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeInfo(2,s.toString(),1);
            }
        });
        etDamao04.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeInfo(3,s.toString(),1);
            }
        });
        etDamao04.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeInfo(4,s.toString(),1);
            }
        });
        etDamao04.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeInfo(5,s.toString(),1);
            }
        });
        etDamao04.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeInfo(6,s.toString(),1);
            }
        });
    }




    @OnClick({R.id.iv_back, R.id.tv_default})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                DeliverData deliverData =  DeliverData.getSingleton();
                deliverData.setDataDa(dataDa);
                deliverData.setDataZhen(dataZhen);
                EventBus.getDefault().post(deliverData);
                finish();
                break;
            case R.id.tv_default:
                DeliverData deliverData1 =  DeliverData.getSingleton();
                deliverData1.setDataDa(dataDa);
                deliverData1.setDataZhen(dataZhen);
                EventBus.getDefault().post(deliverData1);
                finish();
                break;
        }
    }


}
