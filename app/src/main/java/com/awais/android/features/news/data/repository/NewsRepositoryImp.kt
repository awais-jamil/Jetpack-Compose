package com.awais.android.features.news.data.repository

import com.awais.android.core.NEWS_API_KEY
import com.awais.android.features.news.domains.models.NewsResponse
import com.awais.android.features.news.domains.repository.NewsRepository
import com.awais.android.networking.NewsApiClient
import com.awais.api_service.ApiResponse
import com.awais.api_service.ApiService
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(
    private val apiService: ApiService,
) : NewsRepository {
    
    private val apiClient = apiService.createService(NewsApiClient::class.java)
    override suspend fun getNewsArticles(): ApiResponse<NewsResponse> {
        return apiService.request {
            apiClient
                .getNewsArticles("wsj.com", NEWS_API_KEY)
        }
    }
}