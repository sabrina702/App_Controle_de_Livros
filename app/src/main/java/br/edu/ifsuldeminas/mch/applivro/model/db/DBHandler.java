package br.edu.ifsuldeminas.mch.applivro.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "livros.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_BOOKS_CREATE_SQL =
            " CREATE TABLE " +
                    " IF NOT EXISTS books ( " +
                    " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " title text, " +
                    " author text, " +
                    " pages number, " +
                    " status text); ";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_BOOKS_CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
