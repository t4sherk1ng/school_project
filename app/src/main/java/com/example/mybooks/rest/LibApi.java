package com.example.mybooks.rest;

import com.example.mybooks.domain.Book;

public interface LibApi {
    void fillBook();

    void fillGenre();

    void fillAuthor();

    void newBook(Book book);

    void updateBook(int id, String newBookName, String newAuthorName, String newGenreName);

    void deleteBook(int id);
}
