package com.example.movieapp.util

import com.example.movieapp.model.ItemData

object Constants {
    const val DB_NAME = "MyDb"
    const val TABLE_NAME = "MovieTable"
    const val NAME = "name"
    const val AUTHORS = "authors"
    const val MESSAGE = "message"
    const val IS_EDIT = "isEdit"
    const val DATA = "DATA"
    const val ID = "_id"

    fun examle(): List<ItemData>{
        return mutableListOf(
            ItemData(name = "Azizbek", authors = "Azizbek", message = "Azizbek", data = "20.12.12"),
            ItemData(name = "Azizbek", authors = "Azizbek", message = "Azizbek", data = "20.12.12"),
            ItemData(name = "Azizbek", authors = "Azizbek", message = "Azizbek", data = "20.12.12"),
            ItemData(name = "Azizbek", authors = "Azizbek", message = "Azizbek", data = "20.12.12"),
            ItemData(name = "Azizbek", authors = "Azizbek", message = "Azizbek", data = "20.12.12"),
            ItemData(name = "Azizbek", authors = "Azizbek", message = "Azizbek", data = "20.12.12")
        )
    }
}