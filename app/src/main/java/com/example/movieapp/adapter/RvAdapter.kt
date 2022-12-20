package com.example.movieapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.AddActivity
import com.example.movieapp.DetailActivity
import com.example.movieapp.database.MyDatabase
import com.example.movieapp.databinding.ItemLayoutBinding
import com.example.movieapp.model.ItemData

class RvAdapter(private val context: Context): ListAdapter<ItemData, RvAdapter.RvViewHolder>(DiffCallback()) {
    private val myDatabase by lazy { MyDatabase(context) }
    private class DiffCallback: DiffUtil.ItemCallback<ItemData>(){
        override fun areItemsTheSame(oldItem: ItemData, newItem: ItemData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ItemData, newItem: ItemData): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        return RvViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class RvViewHolder(private val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(itemData: ItemData){
            binding.apply {
                itemName.text = itemData.name
                itemName.text = itemData.authors
                itemTime.text = itemData.data
            }
            itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                val bundle = bundleOf("movie" to itemData)
                context.startActivity(intent, bundle)
            }
            binding.btnDelete.setOnClickListener {
                myDatabase.deleteMovie(itemData.id!!)
            }
            binding.btnEdit.setOnClickListener {
                val intent = Intent(context, AddActivity::class.java)
                val bundle = bundleOf("movie" to itemData, "isEdit" to "edit")
                context.startActivity(intent, bundle)
            }
        }
    }
}