package com.example.mybooks.rest;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mybooks.MainActivity;
import com.example.mybooks.adapter.MainRecyclerAdapter;
import com.example.mybooks.domain.Author;
import com.example.mybooks.domain.Book;
import com.example.mybooks.domain.Genre;
import com.example.mybooks.domain.mapper.AuthorMapper;
import com.example.mybooks.domain.mapper.BookMapper;
import com.example.mybooks.domain.mapper.GenreMapper;
import com.example.mybooks.fakedb.LibFakeDb;
import com.example.mybooks.fragment.MainPageFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LibApiImpl implements LibApi {

    public static final String BASE_URL = "http://192.168.1.70:65535";
    private final Context context;

    private Response.ErrorListener errorListener;

    public LibApiImpl(Context context) {
        this.context = context;
        errorListener = new ErrorListenerImpl();
    }

    @Override
    public void fillBook() {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/book";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {

                            LibFakeDb.BOOK_LIST.clear();
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = response.getJSONObject(i);

                                Genre genre = new GenreMapper().genreFromBookJsonArray(jsonObject);

                                Author author = new AuthorMapper().authorFromBookJsonArray(jsonObject);

                                Book book = new BookMapper().bookFromJsonArray(jsonObject, author, genre);
                                LibFakeDb.BOOK_LIST.add(book);
                            }
                            Log.d("BOOK_LIST", LibFakeDb.BOOK_LIST.toString());
                            MainPageFragment mainPageFragment = new MainPageFragment();
                            mainPageFragment.update();
                        } catch (JSONException e) {

                            Log.d("BOOK_LIST", e.getMessage());
                        }

                    }
                },

                errorListener
        );

        queue.add(jsonArrayRequest);
    }

    @Override
    public void fillGenre() {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/genre";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = response.getJSONObject(i);

                                Genre genre = new GenreMapper().genreFromJsonArray(jsonObject);

                                LibFakeDb.GENRE_LIST.add(genre);
                            }
                            Log.d("GENRE_LIST", LibFakeDb.GENRE_LIST.toString());
                        } catch (JSONException e) {

                            Log.d("GENRE_LIST", e.getMessage());
                        }

                    }
                },

                errorListener
        );

        queue.add(jsonArrayRequest);
    }

    @Override
    public void fillAuthor() {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/author";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = response.getJSONObject(i);

                                Author author = new AuthorMapper().authorFromJsonArray(jsonObject);

                                LibFakeDb.AUTHOR_LIST.add(author);
                            }
                            Log.d("AUTHOR_LIST", LibFakeDb.AUTHOR_LIST.toString());
                        } catch (JSONException e) {

                            Log.d("AUTHOR_LIST", e.getMessage());
                        }

                    }
                },

                errorListener
        );

        queue.add(jsonArrayRequest);
    }



    @Override
    public void newBook(Book book) {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/book";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Response", response);

                        fillBook();
                    }
                },

                errorListener
        ) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put("nameBook", book.getName());
                params.put("nameAuthor", book.getAuthor().getName());
                params.put("nameGenre", book.getGenre().getName());

                return params;
            }
        };

        queue.add(postRequest);
    }

    @Override
    public void updateBook(int id, String newBookName, String newAuthorName, String newGenreName) {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/book/" + id + "/";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Response", response);

                        //стоит обновлять локально
                        //но пока так
                        fillBook();
                    }
                },

                errorListener
        ) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put("newBookName", newBookName);
                params.put("newAuthorName", newAuthorName);
                params.put("newGenreName", newGenreName);
                params.put("id", String.valueOf(id));

                return params;
            }
        };

        queue.add(postRequest);
    }

    @Override
    public void deleteBook(int id) {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/book/" + id;

        StringRequest dr = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        fillBook();
                    }
                },

                errorListener
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(id));

                return params;
            }
        };

        queue.add(dr);
    }

    private class ErrorListenerImpl implements Response.ErrorListener {


        @Override
        public void onErrorResponse(VolleyError error) {

            Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
        }
    }

}
