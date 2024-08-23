package br.edu.ifsuldeminas.mch.applivro.model;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable {
    private Integer id;
    private String title;
    private String author;
    private Integer pages;
    private String status;

    public Book(){

    }

    public Book(Integer id, String title,String author, Integer pages, String status) {
        this.id = id;
        setTitle(title);
        setAuthor(author);
        setPages(pages);
        setStatus(status);
    }

    public Integer getId() {
        return id;
    }

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

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return String.format(
                "Livro\n  Título: %s\n  Autor: %s\n  Páginas: %d\n  Status: %s",
                getTitle(), getAuthor(), getPages(), getStatus());
    }
}
