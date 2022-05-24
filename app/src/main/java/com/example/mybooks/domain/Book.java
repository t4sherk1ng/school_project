package com.example.mybooks.domain;

import java.io.Serializable;

public class Book implements Serializable {

    private int id;

    private String name;

    private Author author;

    private Genre genre;

    private String description;

    private String user_id;


    public Book(int id, String name, Author author, Genre genre, String description, String user_id) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }



    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                ", description=" + description +
                ", user_id=" + user_id +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public String getUser_id() {
        return user_id;
    }
}
