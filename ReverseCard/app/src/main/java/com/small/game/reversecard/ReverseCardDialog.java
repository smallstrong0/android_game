package com.small.game.reversecard;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.animation.SlideEnter.SlideTopEnter;
import com.flyco.animation.ZoomExit.ZoomOutBottomExit;
import com.flyco.dialog.widget.base.BaseDialog;

/**
 * 自定义dialog  简简单单一个框
 * Created by smallstrong on 16/5/24.
 */
public class ReverseCardDialog extends BaseDialog<ReverseCardDialog> {

    TextView tvAnswer, tvTitle, tvSure;
    Context context;
    String string, title;
    Bitmap bitmap;
    ImageView iv;

    public ReverseCardDialog(Context context, String str, String title, Bitmap bitmap) {
        super(context);
        this.context = context;
        string = str;
        this.title = title;
        this.bitmap = bitmap;
    }


    @Override
    public View onCreateView() {
        widthScale(0.6f);
        showAnim(new SlideTopEnter());
        dismissAnim(new ZoomOutBottomExit());
        View inflate = View.inflate(context, R.layout.reverse_card_dialog, null);
        tvAnswer = (TextView) inflate.findViewById(R.id.tv_content);
        iv = (ImageView) inflate.findViewById(R.id.iv);
        tvSure = (TextView) inflate.findViewById(R.id.tv_sure);
        tvTitle = (TextView) inflate.findViewById(R.id.tv_title);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        if (!"".equals(string)) {
            tvAnswer.setText(string);
        }
        if (!"".equals(title)) {
            tvTitle.setText(title);
        }
        if (!"".equals(bitmap)) {
            iv.setImageBitmap(bitmap);
        }
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}