package com.ay3524.mvvmlearn;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class ImageViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;

    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.image);
    }
}
