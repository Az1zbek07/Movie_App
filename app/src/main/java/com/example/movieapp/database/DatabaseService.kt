package com.example.movieapp.database

import com.example.movieapp.model.ItemData


interface DatabaseService {
    fun saveMovie(itemData: ItemData)
    fun getAllMovie(): List<ItemData>
    fun deleteMovie(id: Int)
    fun updateMovie(itemData: ItemData)
}