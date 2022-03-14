package sharma.pankaj.newsnow.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sharma.pankaj.newsnow.comman.Constants
import sharma.pankaj.newsnow.data.remote.WebServices
import sharma.pankaj.newsnow.data.repository.NewsListRepositoryImpl
import sharma.pankaj.newsnow.domain.repository.NewsListRepository
import sharma.pankaj.newsnow.domain.utils.PreferenceHandler
import sharma.pankaj.newsnow.presentation.ui.NewsAdapter
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideWebService(): WebServices {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebServices::class.java)
    }

    @Provides
    @Singleton
    fun provideAdapter(): NewsAdapter = NewsAdapter()

    @Provides
    @Singleton
    fun provideLocalRepository(@ApplicationContext context: Context): PreferenceHandler {
        return PreferenceHandler(context)
    }

    @Provides
    fun provideNewsRepository(webservices: WebServices): NewsListRepository {
        return NewsListRepositoryImpl(webservices)
    }

}