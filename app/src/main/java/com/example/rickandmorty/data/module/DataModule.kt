package com.example.rickandmortycompose.data.module


import android.icu.util.TimeUnit
import androidx.room.Room
import com.example.rickandmorty.BuildConfig
import com.example.rickandmortycompose.data.api.CharactersApiService
import com.example.rickandmortycompose.data.api.EpisodeApiService
import com.example.rickandmortycompose.data.api.LocationApiService
import com.example.rickandmortycompose.data.local.FavoritesDao
import com.example.rickandmortycompose.data.local.FavoritesDatabase
import com.example.rickandmortycompose.data.repository.CharactersRepository
import com.example.rickandmortycompose.data.repository.EpisodeRepository
import com.example.rickandmortycompose.data.repository.FavoritesRepository
import com.example.rickandmortycompose.data.repository.LocationRepository
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val dataModule = module{

        single { provideOkHttpClient() }
        single { provideRetrofit(get()) }
        single<CharactersApiService> { get<Retrofit>().create(CharactersApiService::class.java) }
        single<LocationApiService> { get<Retrofit>().create(LocationApiService::class.java) }
        single<EpisodeApiService> { get<Retrofit>().create(EpisodeApiService::class.java) }

        single { Room.databaseBuilder(
                        get(),
                        FavoritesDatabase::class.java,
                        "favorites_database"
                ).build()
        }
        single<FavoritesDao> { get<FavoritesDatabase>().favoritesDao() }

        single { CharactersRepository(get(), get()) }
        single { LocationRepository(get()) }
        single { EpisodeRepository(get()) }
        single { FavoritesRepository(get()) }

}

private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
        }).build()

}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
}

