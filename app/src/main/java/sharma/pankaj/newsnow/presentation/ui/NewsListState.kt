package sharma.pankaj.newsnow.presentation.ui

import sharma.pankaj.newsnow.data.model.Article

data class NewsListState (
    val isLoading: Boolean = false,
    val data: List<Article>? = null,
    val error: String = ""
)