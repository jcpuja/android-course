package com.jcpuja.modernartui;

import android.content.Context;
import android.util.AttributeSet;

public class WhiteRectangle extends ColoredRectangle {
    public WhiteRectangle(Context context, int lineSize) {
        super(context, lineSize);
    }

    public WhiteRectangle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public WhiteRectangle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WhiteRectangle(Context context) {
        super(context);
    }

    @Override
    protected void initColor() {
        baseRed = baseGreen = baseBlue = 255;
    }

    @Override
    public void adjustColor(int modifier) {
        // do nothing
    }
}
