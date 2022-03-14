package sharma.pankaj.newsnow.domain.use_case

import sharma.pankaj.newsnow.domain.repository.NewsListRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import sharma.pankaj.newsnow.comman.Resource
import sharma.pankaj.newsnow.data.model.Article
import java.io.IOException

class NewsUseCase @Inject constructor(private val newsRepository: NewsListRepository) {

    operator fun invoke(url: String): Flow<Resource<List<Article>>> = flow {
        try {
            emit(Resource.Loading())
            val data = newsRepository.listNews(url)
            if (data.status == "ok") {
                emit(Resource.Success(data = data.articles))
            } else {
                emit(Resource.Success(data = emptyList<Article>()))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check Connectivity"))
        } catch (e: Exception) {

        }
    }

}