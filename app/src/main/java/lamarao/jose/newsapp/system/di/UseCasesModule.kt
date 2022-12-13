package lamarao.jose.newsapp.system.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import lamarao.jose.newsapp.database.dao.NewsDao
import lamarao.jose.newsapp.system.network.api.NewsServiceApi
import lamarao.jose.newsapp.usecases.NewsUseCase

@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {

    @ViewModelScoped
    @Provides
    fun providesNewsUseCase(
        newsService: NewsServiceApi,
        newsDao: NewsDao
    ) = NewsUseCase(newsDao, newsService)
}
