package com.android.lee.hashtagproject;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

/**
 * <PRE>
 *     HashTag Span
 * </PRE>
 * Created by Lee on 2017. 09. 08..
 */

public class HashSpan extends ClickableSpan {

    private Context mContext;
    private HashTagClickListener mClickListener;

    public HashSpan(Context pContext, HashTagClickListener pClickListener){
        super();
        mContext = pContext;
        mClickListener = pClickListener;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
    }

    @Override
    public void onClick(View widget) {
        if(widget instanceof TextView) {
            TextView textView = (TextView) widget;
            Spanned spanned = (Spanned) textView.getText();

            int startIndex = spanned.getSpanStart(this);
            int endIndex = spanned.getSpanEnd(this);

            String span = spanned.subSequence(startIndex + 1, endIndex).toString();

            if(mClickListener != null){
                mClickListener.OnHashClick(span);
            }
        }
    }
}
