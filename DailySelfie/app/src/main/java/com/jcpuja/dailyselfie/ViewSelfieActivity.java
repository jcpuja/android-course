package com.jcpuja.dailyselfie;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;


public class ViewSelfieActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selfie);

        Intent i = getIntent();
        File f = new File(i.getData().getPath());
        ImageView selfieView = (ImageView) findViewById(R.id.selfieView);
        selfieView.setImageBitmap(BitmapFactory.decodeFile(f.getAbsolutePath()));
    }
}
