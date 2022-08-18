package com.example.news_app.listener

import com.example.news_app.domain.model.Article

interface NewsListener {
    fun newsListener(news: Article)
}