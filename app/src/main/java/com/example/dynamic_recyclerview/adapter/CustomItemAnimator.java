package com.example.dynamic_recyclerview.adapter;

import android.view.animation.AnimationUtils;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dynamic_recyclerview.R;

public class CustomItemAnimator extends DefaultItemAnimator {
    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        holder.itemView.setAnimation(AnimationUtils.loadAnimation(
                holder.itemView.getContext(),
                R.anim.viewholder_add_anim
        ));
        return super.animateAdd(holder);
    }

    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        holder.itemView.setAnimation(AnimationUtils.loadAnimation(
                holder.itemView.getContext(),
                R.anim.viewholder_remove_anim
        ));
        return super.animateRemove(holder);
    }

    @Override
    public long getAddDuration() {
        return 500;
    }
}
