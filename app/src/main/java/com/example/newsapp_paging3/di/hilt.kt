package com.example.newsapp_paging3.di

import android.app.Application
import android.content.Context
import androidx.databinding.library.BuildConfig
import androidx.room.Room
import com.example.newsapp_paging3.repository.Api
import com.example.newsapp_paging3.repository.Repository
import com.example.newsapp_paging3.repository.RepositoryImpl
import com.example.newsapp_paging3.repository.db.ImagesDao
import com.example.newsapp_paging3.repository.db.ImagesDatabase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    val connectTimeout : Long = 40
    val readTimeout : Long  = 40

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        okHttpClientBuilder.build()
        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext  application: Context): ImagesDatabase {
        return Room.databaseBuilder(application, ImagesDatabase::class.java, "localdb")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(database: ImagesDatabase): ImagesDao {
        return  database.imgDao
    }

    @Singleton
    @Provides
    fun provideRepository(api: Api, @ApplicationContext  context: Context,dao:ImagesDao): Repository {
        return RepositoryImpl(api, context,dao)
    }

  
}