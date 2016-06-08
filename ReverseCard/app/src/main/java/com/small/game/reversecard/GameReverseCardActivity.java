package com.small.game.reversecard;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameReverseCardActivity extends Activity {

    @Bind(R.id.gv)
    GridView gv;
    ReversCardAdapter reversCardAdapter;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.iv_introduce)
    ImageView ivIntroduce;
    @Bind(R.id.tv_ignore)
    TextView tvIgnore;
    private ArrayList<Boolean> data = new ArrayList<>();
    ArrayList<String> gameCardTitleData = new ArrayList<>();
    ArrayList<String> gameCardContentData = new ArrayList<>();
    ArrayList<Bitmap> gameCardIconData = new ArrayList<>();
    ArrayList<Bitmap> gameCardCircleIconData = new ArrayList<>();
    private boolean see =true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_reverse_card_activity);
        ButterKnife.bind(this);
        initData();
        initListerner();
    }

    private void initListerner() {
        tvIgnore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivIntroduce.setVisibility(View.GONE);
                tvIgnore.setVisibility(View.GONE);
                SharedPreferences sharedPreferences = getSharedPreferences("ignore", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("see_card_game",false);
                editor.commit();
            }
        });
        ivIntroduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivIntroduce.setVisibility(View.GONE);
                tvIgnore.setVisibility(View.GONE);
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("cdscdscds", parent + "");
            }
        });

    }

    private void initData() {
        SharedPreferences sharedPreferences = getSharedPreferences("ignore", MODE_PRIVATE);
        see = sharedPreferences.getBoolean("see_card_game", true);
        if(!see){
            ivIntroduce.setVisibility(View.GONE);
            tvIgnore.setVisibility(View.GONE);
        }
        /**
         * 开始装数据
         */
        for (int i = 0; i < 24; i++) {
            data.add(true);
        }
        gameCardTitleData.add("骷髅卡牌");
        gameCardTitleData.add("啤酒卡牌");
        gameCardTitleData.add("爱心卡牌");
        gameCardTitleData.add("闹钟卡牌");

        gameCardContentData.add("做一个你认为最丑的表情");
        gameCardContentData.add("吹瓶子");
        gameCardContentData.add("获香吻一枚");
        gameCardContentData.add("随机向身边女生深情告白");

        gameCardCircleIconData.add(BitmapFactory.decodeResource(getResources(),R.drawable.reverse_card_game_kulou));
        gameCardCircleIconData.add(BitmapFactory.decodeResource(getResources(),R.drawable.reverse_card_game_beer));
        gameCardCircleIconData.add(BitmapFactory.decodeResource(getResources(),R.drawable.reverse_card_game_heart));
        gameCardCircleIconData.add(BitmapFactory.decodeResource(getResources(),R.drawable.reverse_card_game_clock));

        gameCardIconData.add(BitmapFactory.decodeResource(getResources(),R.drawable.reverse_card_kuluo));
        gameCardIconData.add(BitmapFactory.decodeResource(getResources(),R.drawable.reverse_card_beer));
        gameCardIconData.add(BitmapFactory.decodeResource(getResources(),R.drawable.reverse_card_heart));
        gameCardIconData.add(BitmapFactory.decodeResource(getResources(),R.drawable.reverse_card_clock));

        reversCardAdapter = new ReversCardAdapter(this, data,gameCardTitleData,gameCardContentData,gameCardCircleIconData,gameCardIconData);
        gv.setAdapter(reversCardAdapter);

    }
}
