package com.ay3524.mvvmlearn;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ay3524.mvvmlearn.pojo.Wallpaper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageViewHolder> {

    private List<Wallpaper> wallpaperList;

    public ImageRecyclerAdapter() {
        wallpaperList = new ArrayList<>(0);
    }

    public void updateItems(List<Wallpaper> wallpaperList) {
        if (!wallpaperList.isEmpty()) {
            this.wallpaperList.clear();
            this.wallpaperList.addAll(wallpaperList);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.single_item, parent,false);
        return new ImageViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Wallpaper wallpaper = wallpaperList.get(position);

        Picasso.get()
                .load(wallpaper.getWebformatURL())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return wallpaperList.size();
    }
}
