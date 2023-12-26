package com.awais.android.features.news.data.repository

import com.awais.android.features.news.domains.models.NewsResponse
import com.awais.android.features.news.domains.repository.NewsRepository
import com.awais.android.utils.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(

) : NewsRepository {
    override suspend fun getNewsArticles(): Flow<Response<NewsResponse>> {
        TODO("Not yet implemented")
    }
}