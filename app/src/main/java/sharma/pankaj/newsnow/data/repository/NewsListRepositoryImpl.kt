package sharma.pankaj.newsnow.data.repository

import sharma.pankaj.newsnow.data.model.NewsResponse
import sharma.pankaj.newsnow.data.remote.WebServices
import sharma.pankaj.newsnow.domain.repository.NewsListRepository

class NewsListRepositoryImpl(private val webServices: WebServices) : NewsListRepository {


    override suspend fun listNews(url: String): NewsResponse {
        return webServices.fetchNews(url)
    }


}