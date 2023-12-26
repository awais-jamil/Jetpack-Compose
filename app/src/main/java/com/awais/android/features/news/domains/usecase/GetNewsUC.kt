package com.awais.android.features.news.domains.usecase

import com.awais.android.features.news.domains.models.NewsResponse
import com.awais.android.features.news.domains.repository.NewsRepository
import com.awais.api_service.ApiResponse
import javax.inject.Inject

class GetNewsUC @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(): ApiResponse<NewsResponse> =
        newsRepository.getNewsArticles()
}