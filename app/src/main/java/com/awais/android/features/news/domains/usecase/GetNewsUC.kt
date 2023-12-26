package com.awais.android.features.news.domains.usecase

import com.awais.android.features.news.domains.repository.NewsRepository
import javax.inject.Inject

class GetNewsUC @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke() =
        newsRepository.getNewsArticles()
}