package com.jcpuja.dailyselfie;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

public class SelfieThumbnail extends ImageView
{
    public SelfieThumbnail(Context context) {
        super(context);
        GridView.LayoutParams lp = new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.setLayoutParams(lp);
        this.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    public SelfieThumbnail(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelfieThumbnail(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
}
