package com.example.movieapp.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.movieapp.model.ItemData
import com.example.movieapp.util.Constants.AUTHORS
import com.example.movieapp.util.Constants.DB_NAME
import com.example.movieapp.util.Constants.ID
import com.example.movieapp.util.Constants.DATA
import com.example.movieapp.util.Constants.IS_EDIT
import com.example.movieapp.util.Constants.MESSAGE
import com.example.movieapp.util.Constants.NAME
import com.example.movieapp.util.Constants.TABLE_NAME

class MyDatabase(context: Context): SQLiteOpenHelper(context, DB_NAME, null, 1), DatabaseService {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE $TABLE_NAME ($ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, $NAME TEXT NOT NULL, $AUTHORS TEXT NOT NULL, $MESSAGE TEXT NOT NULL, $DATA TEXT NOT NULL, $IS_EDIT TEXT NOT NULL)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    override fun saveMovie(itemData: ItemData) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, itemData.name)
        contentValues.put(AUTHORS, itemData.authors)
        contentValues.put(MESSAGE, itemData.message)
        contentValues.put(DATA, itemData.data)
        contentValues.put(IS_EDIT, itemData.isEdit)
        database.insert(TABLE_NAME, null, contentValues)
        database.close()
    }

    override fun getAllMovie(): List<ItemData> {
        val database = this.readableDatabase
        val movieList = mutableListOf<ItemData>()
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val movie = ItemData(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5))
                movieList.add(movie)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return movieList
    }

    override fun deleteMovie(id: Int) {
        val database = this.writableDatabase
        database.delete(TABLE_NAME, "$ID = ?", arrayOf(id.toString()))
    }

    override fun updateMovie(itemData: ItemData) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, itemData.id)
        contentValues.put(NAME, itemData.name)
        contentValues.put(AUTHORS, itemData.authors)
        contentValues.put(MESSAGE, itemData.message)
        contentValues.put(DATA, itemData.data)
        contentValues.put(IS_EDIT, itemData.isEdit)
        database.update(TABLE_NAME, contentValues, "$ID = ?", arrayOf(itemData.id.toString()))
        database.close()
    }
}