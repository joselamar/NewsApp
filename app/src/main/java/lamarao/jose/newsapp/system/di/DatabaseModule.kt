package lamarao.jose.newsapp.system.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import lamarao.jose.newsapp.database.NewsDatabase
import lamarao.jose.newsapp.database.dao.NewsDao

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

  @Provides fun provideNewsDao(newsDatabase: NewsDatabase): NewsDao = newsDatabase.newsDAO

  @Provides
  fun provideNewsDatabase(@ApplicationContext context: Context): NewsDatabase =
      Room.databaseBuilder(context.applicationContext, NewsDatabase::class.java, "news_database.db")
          .build()
}
