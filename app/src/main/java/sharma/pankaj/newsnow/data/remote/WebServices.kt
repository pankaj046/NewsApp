package sharma.pankaj.newsnow.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import sharma.pankaj.newsnow.data.model.NewsResponse

interface WebServices {

    @GET
    suspend fun fetchNews(
        @Url url: String
    ): NewsResponse
}