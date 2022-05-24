package com.example.mybooks.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mybooks.R;

import com.example.mybooks.domain.Book;

import java.util.List;


public class MainRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private List<Book> bookList;
    private Context context;

    public MainRecyclerAdapter(Context context, List<Book> bookList) {

        this.inflater = LayoutInflater.from(context);
        this.bookList = bookList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        final public TextView nameView, descriptionView;
        MyViewHolder(View view){
            super(view);
            nameView = view.findViewById(R.id.textView);
            descriptionView = view.findViewById(R.id.textView2);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.main_adapter_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull RecyclerView.ViewHolder holder,
            @SuppressLint("RecyclerView") int position
    ) {

        Book book = bookList.get(position);

        ((MyViewHolder) holder).nameView.setText(book.getName());
        ((MyViewHolder) holder).descriptionView.setText(book.getDescription());


    }

    @Override
    public int getItemCount() {

        return bookList.size();
    }
}