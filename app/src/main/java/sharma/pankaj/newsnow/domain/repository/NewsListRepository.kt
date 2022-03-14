package sharma.pankaj.newsnow.domain.repository

import sharma.pankaj.newsnow.data.model.NewsResponse

interface NewsListRepository {
    suspend fun listNews(url: String): NewsResponse
}