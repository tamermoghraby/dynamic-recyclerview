package com.example.dynamic_recyclerview.utils;

import androidx.recyclerview.widget.DiffUtil;


import com.example.dynamic_recyclerview.model.RecyclerViewItem;

import java.util.List;

public class RecyclerViewItemDiffCallback extends DiffUtil.Callback {
    private List<RecyclerViewItem> oldItems;
    private List<RecyclerViewItem> newItems;

    public RecyclerViewItemDiffCallback(List<RecyclerViewItem> oldItems, List<RecyclerViewItem> newItems) {
        this.oldItems = oldItems;
        this.newItems = newItems;
    }

    @Override
    public int getOldListSize() {
        return oldItems.size();
    }

    @Override
    public int getNewListSize() {
        return newItems.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldItems.get(oldItemPosition).getName() == newItems.get(newItemPosition).getName();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        RecyclerViewItem oldItem = oldItems.get(oldItemPosition);
        RecyclerViewItem newItem = newItems.get(newItemPosition);

        return oldItem.equals(newItem);
    }
}
