package br.edu.ifsuldeminas.mch.applivro.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsuldeminas.mch.applivro.model.Book;

public class BookDAO extends DAO{
    public BookDAO(Context context) {
        super(context);
    }

    public boolean save(Book book){
        SQLiteDatabase dataBase = openToWrite();

        ContentValues contentValues = new ContentValues();
        contentValues.put("title", book.getTitle());
        contentValues.put("author", book.getAuthor());
        contentValues.put("pages", book.getPages());
        contentValues.put("status", book.getStatus());

        dataBase.insert("books", null, contentValues);

        close();

        return true;
    }

    public List<Book> loadBooks() {
        SQLiteDatabase dataBase = openToRead();
        List<Book> books = new ArrayList<Book>();
        String sql = "SELECT * FROM books;";
        Cursor cursor = dataBase.rawQuery(sql, null);

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            String author = cursor.getString(cursor.getColumnIndexOrThrow("author"));
            Integer pages = cursor.getInt(cursor.getColumnIndexOrThrow("pages"));
            String status = cursor.getString(cursor.getColumnIndexOrThrow("status"));

            Book book = new Book(id, title, author, pages, status);
            books.add(book);
        }
        cursor.close();
        close();
        return books;
    }

    public void delete(Book book) {
        SQLiteDatabase dataBase = openToWrite();

        String[] params = {book.getId().toString()};
        dataBase.delete("books", "id = ?", params);

       close();
    }

    public void update(Book book) {
        SQLiteDatabase dataBase = openToWrite();

        ContentValues contentValues = new ContentValues();
        contentValues.put("title", book.getTitle());
        contentValues.put("author", book.getAuthor());
        contentValues.put("pages", book.getPages());
        contentValues.put("status", book.getStatus());

        String[] params = {book.getId().toString()};
        dataBase.update("books", contentValues, "id = ?", params);

        close();
    }

}
