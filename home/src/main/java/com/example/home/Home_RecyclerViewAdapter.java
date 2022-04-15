package com.example.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.common.object.Book;
import com.example.common.session.Session;
import com.example.home.info.Home_InfoActivity;

import java.util.ArrayList;
import java.util.List;

public class Home_RecyclerViewAdapter extends RecyclerView.Adapter<Home_RecyclerViewAdapter.ViewHolder> {
    private final Context context;
    private List<Book> list;

    public Home_RecyclerViewAdapter(Context context, List<Book> list) {
        this.context = context;
        this.list = list != null ? list : new ArrayList<>();
    }

    @NonNull
    @Override
    public Home_RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.home_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Home_RecyclerViewAdapter.ViewHolder holder, int position) {
        Book book = list.get(position);
        Glide.with(context).load(book.getImg()).into(holder.img);
        holder.title.setText(book.getTitle());
        holder.intro.setText(book.getIntro());
        holder.state.setText(book.getState());
        holder.type.setText(book.getType());
        holder.type2.setText(book.getType2());
        holder.itemView.setOnClickListener(view -> {
            Session.book = book;
            context.startActivity(new Intent(context, Home_InfoActivity.class));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title;
        TextView intro;
        TextView state;
        TextView type;
        TextView type2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.home_item_img);
            title = itemView.findViewById(R.id.home_item_title);
            intro = itemView.findViewById(R.id.home_item_intro);
            state = itemView.findViewById(R.id.home_item_state);
            type = itemView.findViewById(R.id.home_item_type);
            type2 = itemView.findViewById(R.id.home_item_type2);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void update(List<Book> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void add(Book book) {
        this.list.add(book);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void removeAll() {
        this.list = new ArrayList<>();
        notifyDataSetChanged();
    }
}
