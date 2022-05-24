package com.example.mybooks.domain.mapper;

import com.example.mybooks.domain.Author;
import com.example.mybooks.domain.Book;
import com.example.mybooks.domain.Genre;


import org.json.JSONException;
import org.json.JSONObject;

public class BookMapper {

    public Book bookFromJsonArray(JSONObject jsonObject, Author author, Genre genre) {

        Book book = null;

        try {
            book = new Book(
                    jsonObject.getInt("id"),
                    jsonObject.getString("name"),
                    author,
                    genre,
                    jsonObject.getString("description"),
                    jsonObject.getString("user_id")
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return book;
    }

}