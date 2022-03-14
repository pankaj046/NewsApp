package sharma.pankaj.newsnow.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import sharma.pankaj.newsnow.comman.Resource
import sharma.pankaj.newsnow.domain.use_case.NewsUseCase
import sharma.pankaj.newsnow.presentation.ui.NewsListState
import javax.inject.Inject

@HiltViewModel
open class NewsViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    private val _newsList = MutableStateFlow<NewsListState>(NewsListState())
    var newsList : StateFlow<NewsListState> = _newsList

    fun getNews(url: String){
        newsUseCase(url).onEach {
            when(it){
                is Resource.Loading -> {
                    _newsList.value = NewsListState(isLoading = true)
                }
                is Resource.Success -> {
                    _newsList.value = NewsListState(data = it.data)
                }
                is Resource.Error -> {
                    _newsList.value = NewsListState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }

}