package com.example.mybooks.domain.mapper;

import com.example.mybooks.domain.Author;

import org.json.JSONException;
import org.json.JSONObject;

public class AuthorMapper {

    public Author authorFromBookJsonArray(JSONObject jsonObject) {

        Author author = null;
        try {

            author = new Author(
                    jsonObject.getJSONObject("authorDto").getInt("id"),
                    jsonObject.getJSONObject("authorDto").getString("name")
            );
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return author;
    }

    public Author authorFromJsonArray(JSONObject jsonObject) {

        Author author = null;
        try {

            author = new Author(
                    jsonObject.getInt("id"),
                    jsonObject.getString("name")
            );
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return author;
    }

}