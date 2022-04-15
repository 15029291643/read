package com.example.home.info.catalog.catalog;

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
import com.example.common.activity.Show;
import com.example.common.object.Chapter;
import com.example.home.R;
import com.example.home.info.catalog.Home_CatalogActivity;
import com.example.home.info.read.Home_ReadActivity;

import java.util.List;

public class Home_CatalogAdapter extends RecyclerView.Adapter<Home_CatalogAdapter.ViewHolder> {
    private final Context context;
    private List<Chapter> list;

    public Home_CatalogAdapter(Context context, List<Chapter> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.home_item_catalog, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Chapter chapter = list.get(position);
        holder.title.setText(chapter.getTitle());
        // holder.time.setText(chapter.getTime());
        holder.itemView.setOnClickListener(view -> {
            Session.chapterPosition = position;
            Show.log(Session.getChapter().toString());
            context.startActivity(new Intent(context, Home_ReadActivity.class));
            Home_CatalogActivity.toFinish();
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
            title = itemView.findViewById(R.id.home_item_catalog_title);
            time = itemView.findViewById(R.id.home_item_catalog_time);
        }
    }

    public void update(List<Chapter> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
