package br.edu.ifsuldeminas.mch.applivro.service;

public class BookInfo {
    private String title;
    private String author;
    private String description;

    // Construtor
    public BookInfo(String title, String author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;
    }

    // Getters e Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
