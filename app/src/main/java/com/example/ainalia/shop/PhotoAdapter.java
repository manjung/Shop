package com.example.ainalia.shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Ainalia on 2016/2/1.
 */
public class PhotoAdapter extends BaseAdapter
{
    private List<String> photoURLs;
    private ImageLoader imageLoader ;
    private Context mContext;


    public PhotoAdapter (Context context, List<String> objects, ImageLoader imageLoader)
    {
        this.mContext=context;
        this.photoURLs=objects;
        this.imageLoader= imageLoader;
    }


    @Override
    public int getCount() {
        return photoURLs.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder holder;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.photo_view, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) view.findViewById(R.id.photo);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        imageLoader.displayImage(photoURLs.get(position), holder.image);

        return view;
    }

    private class ViewHolder {
        public ImageView image;
    }
}
