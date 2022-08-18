package com.example.news_app.web_services

import com.example.news_app.domain.model.News
import com.example.news_app.domain.sealed.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.Serializable

object DataManger: Serializable {
    fun getNews(): Flow<State<News>> = flow {
        emit(State.Loading)
        emit(Client.getNewsData())
    }.flowOn(Dispatchers.IO)
}