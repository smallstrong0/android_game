package com.small.game.circlegame;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.flyco.animation.SlideEnter.SlideTopEnter;
import com.flyco.animation.ZoomExit.ZoomOutBottomExit;
import com.flyco.dialog.widget.base.BaseDialog;

import org.greenrobot.eventbus.EventBus;

/**
 * 自定义dialog  简简单单一个框
 * Created by smallstrong on 16/5/24.
 */
public class ResultDialog extends BaseDialog<ResultDialog> {

    TextView tvAnswer;
    Context context;
    String string;


    public ResultDialog(Context context,String str) {
        super(context);
        this.context =context;
        string =str;
    }


    @Override
    public View onCreateView() {
        widthScale(1.0f);
        showAnim(new SlideTopEnter());
        dismissAnim(new ZoomOutBottomExit());
        View inflate = View.inflate(context, R.layout.result_dialog, null);
        tvAnswer = (TextView) inflate.findViewById(R.id.tv_answer);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        if (!"".equals(string)) {
            tvAnswer.setText(string);
        }
        tvAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }




}