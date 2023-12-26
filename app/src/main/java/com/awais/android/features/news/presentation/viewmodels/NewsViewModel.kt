package com.awais.android.features.news.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.awais.android.features.news.domains.models.NewsArticle
import com.awais.android.features.news.domains.usecase.GetNewsUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUC: GetNewsUC,
) : ViewModel() {
    
    val loading = MutableStateFlow(false)
    private val error = MutableStateFlow("")
    
    private val _news = MutableStateFlow<List<NewsArticle>>(
        emptyList()
    )
    val newsArticles = _news.asStateFlow()
    
    fun fetchNewsArticles() {
        loading.value = true
        viewModelScope.launch {
            val response = getNewsUC.invoke()
            loading.value = false
            if (response.data != null && response.error == null) {
                _news.value = response.data!!.articles
            } else {
                error.value = response.error!!.message ?: "Unknown error"
            }
        }
    }
}