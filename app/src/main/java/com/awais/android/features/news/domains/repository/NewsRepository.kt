package com.awais.android.features.news.domains.repository

import com.awais.android.features.news.domains.models.NewsResponse
import com.awais.android.utils.Response
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNewsArticles(): Flow<Response<NewsResponse>>
}