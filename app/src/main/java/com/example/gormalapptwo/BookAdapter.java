package com.example.gormalapptwo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{

    private ArrayList<Book> listdata;
    Context context;

    public BookAdapter(ArrayList<Book> listdata, Context context) {
        this.listdata = listdata;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.book_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Book myListData = listdata.get(position);
        holder.textViewBookName.setText(listdata.get(position).getBookName());
        holder.textViewBookDescription.setText(listdata.get(position).getBookDesc());
        holder.textViewBookPrice.setText(listdata.get(position).getBookPrice());
        holder.textViewBookAuthor.setText(listdata.get(position).getBookAuthor());
        //holder.imageView.setImageResource(listdata[position].getImgId());;
        //listdata.get(position).getUrl()
        Log.d("url_image",listdata.get(position).getBookImgUrl());
        // Glide.with(context).load(listdata.get(position).getUrl()).into(holder.imageView);

        Glide.with(context).load(listdata.get(position).getBookImgUrl()).
                into(holder.bookImageView);
    }

    @Override
    public int getItemCount() {
        if(listdata!=null)
        {
            return listdata.size();

        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView bookImageView;
        public TextView textViewBookName;
        public TextView textViewBookDescription;
        public TextView textViewBookAuthor;
        public TextView textViewBookPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.bookImageView = (ImageView) itemView.findViewById(R.id.img_book);
            this.textViewBookName = (TextView) itemView.findViewById(R.id.tv_book_name);
            this.textViewBookDescription = (TextView) itemView.findViewById(R.id.tv_book_description);
            this.textViewBookAuthor = (TextView) itemView.findViewById(R.id.tv_book_author);
            this.textViewBookPrice = (TextView) itemView.findViewById(R.id.tv_book_price);

        }

    }
}
