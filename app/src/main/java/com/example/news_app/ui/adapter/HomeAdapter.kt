package com.example.news_app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news_app.R
import com.example.news_app.databinding.ItemPostBinding
import com.example.news_app.domain.model.Article
import com.example.news_app.listener.NewsListener


class HomeAdapter(private val listArticle: List<Article>, val listener: NewsListener) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val currentItem = listArticle[position]
        holder.binding.apply {
            titlePost.text = currentItem.title
            Glide.with(holder.itemView).load(currentItem.urlToImage).into(imageNews)
            root.setOnClickListener {
                listener.newsListener(currentItem)
            }
        }
    }

    override fun getItemCount(): Int = listArticle.size

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemPostBinding.bind(itemView)
    }
}