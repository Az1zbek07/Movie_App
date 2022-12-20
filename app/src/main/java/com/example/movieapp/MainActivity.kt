package com.example.movieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.movieapp.adapter.RvAdapter
import com.example.movieapp.database.MyDatabase
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.model.ItemData
import com.example.movieapp.util.Constants

class MainActivity : AppCompatActivity() {
    private val myDatabase by lazy { MyDatabase(this) }
    private val rvAdapter by lazy { RvAdapter(this) }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val itemData = ItemData(name = "Azizbek", authors = "Azizbek", message = "Azizbek", data = "Azizbek")
        myDatabase.saveMovie(itemData)
        myDatabase.saveMovie(itemData)
        myDatabase.saveMovie(itemData)
        myDatabase.saveMovie(itemData)
        myDatabase.saveMovie(itemData)
        myDatabase.saveMovie(itemData)
        myDatabase.saveMovie(itemData)

        binding.rv.apply {
            rvAdapter
        }
        rvAdapter.submitList(myDatabase.getAllMovie())
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_menu, menu)
        return true
    }

    fun addItem(item: MenuItem){
        val intent = Intent(this, AddActivity::class.java)
        intent.putExtra("isEdit", "")
        startActivity(intent)
    }
}