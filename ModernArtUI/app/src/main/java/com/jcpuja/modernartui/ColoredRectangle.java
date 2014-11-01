package com.jcpuja.modernartui;

import android.content.Context;
import android.graphics.Color;
import android.print.PrintAttributes;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.Random;

public class ColoredRectangle extends View {

    public static final int COLOR_CHANNEL_RANGE = 256;
    private static final int MARGIN_SIZE = 10;
    protected int baseRed;
    protected int baseGreen;
    protected int baseBlue;

    boolean invertRedModifier;
    boolean invertGreenModifier;
    boolean invertBlueModifier;

    float redModifierFactor;
    float greenModifierFactor;
    float blueModifierFactor;

    Random random = new Random();

    public ColoredRectangle(Context context) {
        super(context);
    }

    public ColoredRectangle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ColoredRectangle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ColoredRectangle(Context context, int lineSize) {
        super(context);

        initColor();

        setBackgroundColor(Color.rgb(baseRed, baseGreen, baseBlue));

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, (1f / lineSize));
        layoutParams.setMargins(MARGIN_SIZE,MARGIN_SIZE,MARGIN_SIZE,MARGIN_SIZE);
        setLayoutParams(layoutParams);

    }

    protected void initColor() {
        baseRed = random.nextInt(COLOR_CHANNEL_RANGE);
        baseGreen = random.nextInt(COLOR_CHANNEL_RANGE);
        baseBlue = random.nextInt(COLOR_CHANNEL_RANGE);

        invertRedModifier = random.nextBoolean();
        invertGreenModifier = random.nextBoolean();
        invertBlueModifier = random.nextBoolean();

        redModifierFactor = random.nextFloat() + 0.7f;
        greenModifierFactor = random.nextFloat() + 0.7f;
        blueModifierFactor = random.nextFloat() + 0.7f;
    }

    public void adjustColor(int modifier) {


        int newRed = adjustColorChannel(baseRed, modifier, redModifierFactor, invertRedModifier);
        int newGreen = adjustColorChannel(baseGreen, modifier, greenModifierFactor, invertGreenModifier);
        int newBlue = adjustColorChannel(baseBlue, modifier, blueModifierFactor, invertBlueModifier);

        setBackgroundColor(Color.rgb(newRed, newGreen, newBlue));
    }

    private int adjustColorChannel(int baseValue, int modifier, float modifierFactor, boolean invert) {
        int channelModifier = invert ? -1 * modifier : modifier;
        channelModifier = (int) (channelModifier * modifierFactor);
        return Math.max(0, Math.min(baseValue + channelModifier, COLOR_CHANNEL_RANGE - 1));
    }
}
