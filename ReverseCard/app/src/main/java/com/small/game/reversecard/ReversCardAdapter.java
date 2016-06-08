package com.small.game.reversecard;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by smallstrong on 16/6/3.
 */
public class ReversCardAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Boolean> data = new ArrayList<>();
    ArrayList<String> gameCardTitleData = new ArrayList<>();
    ArrayList<String> gameCardContentData = new ArrayList<>();
    ArrayList<Bitmap> gameCardIconData = new ArrayList<>();
    ArrayList<Bitmap> gameCardCircleIconData = new ArrayList<>();
    Random random = new Random();
    private int num = 0;
    private MediaPlayer mPlayer = null; // 音频
    private MediaPlayer mPlayer1 = null;

    public ReversCardAdapter(Context context, ArrayList<Boolean> data, ArrayList<String> gameCardTitleData, ArrayList<String> gameCardContentData, ArrayList<Bitmap> gameCardCircleIconData, ArrayList<Bitmap> gameCardIconData) {
        this.data = data;
        this.context = context;
        this.gameCardCircleIconData = gameCardCircleIconData;
        this.gameCardIconData = gameCardIconData;
        this.gameCardContentData = gameCardContentData;
        this.gameCardTitleData = gameCardTitleData;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.revers_card_item, null);
            viewHolder.iv = (ImageView) convertView.findViewById(R.id.iv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final ViewHolder finalViewHolder = viewHolder;
            viewHolder.iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(data.get(position) == true){
                        data.remove(position);
                        data.add(position, false);
                        rotateyAnimRun(finalViewHolder.iv);
                    }else {

                    }
                }
            });

        return convertView;
    }


    public void rotateyAnimRun(final ImageView view) {
        mPlayer = MediaPlayer.create(context, R.raw.card);
        mPlayer.start();
        ObjectAnimator animator = ObjectAnimator//
                .ofFloat(view, "rotationY", 0.0F, 90.0F);//
        animator.setDuration(500);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                num = random.nextInt(gameCardTitleData.size());
                view.setImageBitmap(gameCardIconData.get(num));
                rotateyAnimRun2(view);
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

    ReverseCardDialog reverseCardDialog;

    public void rotateyAnimRun2(final ImageView view) {
        ObjectAnimator animator = ObjectAnimator//
                .ofFloat(view, "rotationY", 90, 180.0F);//
        animator.setDuration(500);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mPlayer.stop();
                mPlayer.release();

                reverseCardDialog = new ReverseCardDialog(context, gameCardContentData.get(num), gameCardTitleData.get(num), gameCardCircleIconData.get(num));
                reverseCardDialog.show();

                mPlayer1 = MediaPlayer.create(context,R.raw.answer);
                mPlayer1.start();
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

    class ViewHolder {
        ImageView iv;
    }
}
