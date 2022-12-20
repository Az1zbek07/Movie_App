package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.databinding.ActivityDetailBinding
import com.example.movieapp.model.ItemData

class DetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val itemData = intent.getSerializableExtra("movie") as ItemData

        itemData.let {
            binding.movieName.text = "Name: ${it.name}"
            binding.movieAuthors.text = "Authors: ${it.authors}"
            binding.movieData.text = "Date: ${it.data}"
            binding.movieMessage.text ="About movie: ${it.message}"
        }
    }
}