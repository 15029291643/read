package com.example.search.writer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.session.Session;
import com.example.common.activity.Show;
import com.example.common.object.Book;
import com.example.common.util.ImgUtil;
import com.example.search.R;
import com.example.search.writer.info.Search_InfoActivity;


import java.util.ArrayList;
import java.util.List;

public class Search_BookAdapter extends RecyclerView.Adapter<Search_BookAdapter.ViewHolder> {
    private final Context context;
    private List<Book> list;

    public Search_BookAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    @NonNull
    @Override
    public Search_BookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.search_item_book, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Search_BookAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Book book = list.get(position);
        holder.title.setText(book.getTitle());
        holder.intro.setText(book.getIntro());
        Bitmap bitmap = ImgUtil.stringToBitmap(book.getImg());
        holder.img.setImageBitmap(bitmap);
        // Glide.with(context).load(book.getImg()).into(holder.img);
        holder.itemView.setOnClickListener(view -> {
            Session.book = book;
            Show.log(Session.book.toString());
            Session.bookPosition = position;
            context.startActivity(new Intent(context, Search_InfoActivity.class));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();  // 测试
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
            img = itemView.findViewById(R.id.search_item_img);
            title = itemView.findViewById(R.id.search_item_title);
            intro = itemView.findViewById(R.id.search_item_intro);
            state = itemView.findViewById(R.id.search_item_author);
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

    public void set(int position, Book book) {
        this.list.set(position, book);
        notifyDataSetChanged();
    }
}
