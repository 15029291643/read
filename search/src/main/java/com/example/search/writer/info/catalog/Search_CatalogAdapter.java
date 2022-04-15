package com.example.search.writer.info.catalog;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.session.Session;
import com.example.common.object.Chapter;
import com.example.search.R;
import com.example.search.writer.info.edit.Search_EditActivity;

import java.util.ArrayList;
import java.util.List;

public class Search_CatalogAdapter extends RecyclerView.Adapter<Search_CatalogAdapter.ViewHolder> {
    private final Context context;
    private List<Chapter> list;

    public Search_CatalogAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.search_item_catalog, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Chapter chapter = list.get(position);
        holder.title.setText(chapter.getTitle());
        holder.itemView.setOnClickListener(view -> {
            Session.chapterPosition = position;
            context.startActivity(new Intent(context, Search_EditActivity.class));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.search_item_catalog_title);
            time = itemView.findViewById(R.id.search_item_catalog_time);
        }
    }

    public void update(List<Chapter> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void set(int position, Chapter chapter) {
        this.list.set(position, chapter);
        notifyDataSetChanged();
    }

    public void add(Chapter chapter) {
        this.list.add(chapter);
        notifyDataSetChanged();
    }
}
