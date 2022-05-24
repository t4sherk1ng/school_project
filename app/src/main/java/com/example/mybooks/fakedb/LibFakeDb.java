package com.example.mybooks.fakedb;

import com.example.mybooks.domain.Author;
import com.example.mybooks.domain.Book;
import com.example.mybooks.domain.Genre;

import java.util.ArrayList;
import java.util.List;

public class LibFakeDb {

    private LibFakeDb(){}

    public static final List<Book> BOOK_LIST = new ArrayList<>();

    public static final List<Genre> GENRE_LIST = new ArrayList<>();

    public static final List<Author> AUTHOR_LIST = new ArrayList<>();
}
