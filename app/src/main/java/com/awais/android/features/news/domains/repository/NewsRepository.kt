package com.awais.android.features.news.domains.repository

import com.awais.android.features.news.domains.models.NewsResponse
import com.awais.api_service.ApiResponse

interface NewsRepository {
    suspend fun getNewsArticles(): ApiResponse<NewsResponse>
}