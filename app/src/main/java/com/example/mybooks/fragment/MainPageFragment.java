package com.example.mybooks.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybooks.domain.Book;
import com.example.mybooks.adapter.MainRecyclerAdapter;
import com.example.mybooks.R;
import com.example.mybooks.rest.LibApi;
import com.example.mybooks.rest.LibApiImpl;

import java.util.ArrayList;

public class MainPageFragment extends Fragment {

    private static final String TAG = "myLogs";
    ArrayList<Book> books_arr = new ArrayList<>();
    MainRecyclerAdapter adapter;
    private final LibApiImpl libraryApi = new LibApiImpl(getContext());

    @SuppressLint("ResourceType")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        booksInit();
        new LibApiImpl(getContext()).fillBook();
        try {
            View view = inflater.inflate(R.layout.main_page_fragment, container, false);
            RecyclerView books = view.findViewById(R.id.Items);
            adapter = new MainRecyclerAdapter(getActivity(), books_arr);

            books.setAdapter(adapter);
            return view;
        } catch (Exception e) {
            Log.e(TAG, "MainPage", e);
            throw e;
        }
    }

//    protected void booksInit() {
//        books_arr.add(new Book(1,"Name1", null, null, "description1", null));
//        books_arr.add(new Book(2,"Name2", null, null, "description2", null));
//        books_arr.add(new Book(3,"Name3", null, null, "description3", null));
//        books_arr.add(new Book(4,"Name4", null, null, "description4", null));
//        books_arr.add(new Book(5,"Name5", null, null, "description5", null));
//    }



//    @Override
//    public void onResume() {
//        super.onResume();
//
//        libraryApi.fillBook();
//    }

    public void update() {
        adapter.notifyDataSetChanged();
    }
}
