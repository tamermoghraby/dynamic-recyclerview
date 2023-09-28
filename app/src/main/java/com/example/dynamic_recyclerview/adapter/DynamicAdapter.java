package com.example.dynamic_recyclerview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dynamic_recyclerview.R;
import com.example.dynamic_recyclerview.utils.RecyclerViewItemDiffCallback;
import com.example.dynamic_recyclerview.model.RecyclerViewItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DynamicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<RecyclerViewItem> items;
    private List<RecyclerViewItem> filteredItems;
    private boolean isGridMode;

    public DynamicAdapter(List<RecyclerViewItem> items, boolean isGridMode) {
        this.items = items;
        this.filteredItems = new ArrayList<>(items);
        this.isGridMode = isGridMode;
    }

    public void setGridMode(boolean gridMode) {
        isGridMode = gridMode;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if(isGridMode) {
            View view = inflater.inflate(R.layout.grid_item, parent, false);
            return new GridViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.list_item, parent, false);
            return new ListViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecyclerViewItem item = items.get(position);

        if(holder instanceof GridViewHolder) {
            String url = item.getImageUrl();
            Picasso.get().load(item.getImageUrl()).into(((GridViewHolder) holder).image);

        } else if (holder instanceof ListViewHolder) {
            Picasso.get().load(item.getImageUrl()).into(((ListViewHolder) holder).image);
            ((ListViewHolder) holder).text1.setText(item.getName());
            ((ListViewHolder) holder).text2.setText(item.getOffer());
        }
    }

    public void updateDataSet(List<RecyclerViewItem> newItems) {
        filteredItems.clear();
        filteredItems.addAll(newItems);


        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new RecyclerViewItemDiffCallback(items, newItems));
        items = newItems;
        diffResult.dispatchUpdatesTo(this);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    private static class GridViewHolder extends RecyclerView.ViewHolder {

        ImageView image;

        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.itemImage);
        }

    }

    private static class ListViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView text1;
        TextView text2;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView);
            text1 = itemView.findViewById(R.id.text1);
            text2 = itemView.findViewById(R.id.text2);
        }
    }

}

