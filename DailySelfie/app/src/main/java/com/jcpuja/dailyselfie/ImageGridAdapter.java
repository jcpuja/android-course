package com.jcpuja.dailyselfie;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImageGridAdapter extends BaseAdapter {

    private List<File> photos;
    private File storageDir;
    private Activity activity;

    public ImageGridAdapter(File storageDir, Activity activity) {
        this.storageDir = storageDir;
        this.activity = activity;
        refreshPhotos();
    }

    public void refreshPhotos() {
        photos = new ArrayList<File>(Arrays.asList(storageDir.listFiles()));
        notifyDataSetChanged();
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
        SelfieThumbnail thumbnail = (SelfieThumbnail) convertView;

        if (thumbnail == null) {
            thumbnail = new SelfieThumbnail(activity);
        }

        Bitmap bitmap = BitmapFactory.decodeFile(photos.get(position).getAbsolutePath());
        thumbnail.setImageBitmap(bitmap);
        return thumbnail;
    }
}
