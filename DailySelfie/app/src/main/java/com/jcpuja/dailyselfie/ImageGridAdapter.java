package com.jcpuja.dailyselfie;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImageGridAdapter extends BaseAdapter {

    private List<File> photos;
    private Activity activity;

    public ImageGridAdapter(File storageDir, Activity activity) {
        this.activity = activity;
        photos = new ArrayList<File>(Arrays.asList(storageDir.listFiles()));
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public Object getItem(int position) {
        return photos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = (ImageView) convertView;

        if (imageView == null) {
            imageView = new ImageView(activity);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        Bitmap bitmap = BitmapFactory.decodeFile(photos.get(position).getAbsolutePath());
        imageView.setImageBitmap(bitmap);
        return imageView;
    }
}
