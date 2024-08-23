package br.edu.ifsuldeminas.mch.applivro.model.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DAO {
    private static DBHandler dbHandler = null;
    private SQLiteDatabase database = null;

    DAO(Context context) {
        if (dbHandler == null)
            dbHandler = new DBHandler(context);
    }

    SQLiteDatabase openToWrite() throws SQLException {
        return dbHandler.getWritableDatabase();
    }

    SQLiteDatabase openToRead() throws SQLException {
        return dbHandler.getReadableDatabase();
    }

    public void close() {
        if (database != null && database.isOpen()) {
            database.close();
        }
        if (dbHandler != null) {
            dbHandler.close();
        }
    }
}
