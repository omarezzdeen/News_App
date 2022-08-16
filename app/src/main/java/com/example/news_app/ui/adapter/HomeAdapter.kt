package com.example.news_app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news_app.R
import com.example.news_app.databinding.ItemPostBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.binding.apply {
            titlePost.text = "Post Title"
            imageNews.setImageBitmap(null)
        }
    }

    override fun getItemCount(): Int = 10

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemPostBinding.bind(itemView)
    }
}