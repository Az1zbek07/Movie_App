package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.database.MyDatabase
import com.example.movieapp.databinding.ActivityAddBinding
import com.example.movieapp.model.ItemData
import com.google.android.material.snackbar.Snackbar

class AddActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddBinding.inflate(layoutInflater) }
    private val myDatabase by lazy { MyDatabase(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val isEdit = intent.getStringExtra("isEdit").toString()
        if (isEdit == ""){
            binding.btnSave.setOnClickListener {
                val name = binding.editName.text.toString().trim()
                val authors = binding.editAuthor.text.toString().trim()
                val data = binding.editData.text.toString().trim()
                val message = binding.editMessage.text.toString().trim()
                if (name.isNotBlank() && authors.isNotBlank() && data.isNotBlank()){
                    val itemData = ItemData(name = name, data = data, message = message, authors = authors)
                    myDatabase.saveMovie(itemData)
                    Snackbar.make(binding.root, "Saved", Snackbar.LENGTH_SHORT).show()
                    finish()
                }else{
                    Snackbar.make(binding.root, "Please enter values", Snackbar.LENGTH_SHORT).show()
                }
            }
        }

        if (isEdit == "edit"){
            val itemData = intent.getSerializableExtra("movie") as ItemData
            binding.apply {
                editAuthor.setText(itemData.authors)
                editName.setText(itemData.name)
                editMessage.setText(itemData.message)
                editData.setText(itemData.data)
            }

            binding.btnSave.setOnClickListener {
                if (binding.editName.text.isNotBlank() && binding.editAuthor.text.isNotBlank() && binding.editData.text.isNotBlank()){
                    val name = binding.editName.text.toString().trim()
                    val authors = binding.editAuthor.text.toString().trim()
                    val data = binding.editData.text.toString().trim()
                    val message = binding.editMessage.text.toString().trim()

                    val itemData2 = ItemData(id = itemData.id, name = name, data = data, message = message, authors = authors)
                    myDatabase.updateMovie(itemData2)
                    Snackbar.make(binding.root, "Edited", Snackbar.LENGTH_SHORT).show()
                    finish()
                }else{
                    Snackbar.make(binding.root, "Please enter values", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}