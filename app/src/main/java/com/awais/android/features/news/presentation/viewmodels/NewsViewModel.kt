package com.awais.android.features.news.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.awais.android.features.news.domains.usecase.GetNewsUC
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUC: GetNewsUC,
) : ViewModel() {
}