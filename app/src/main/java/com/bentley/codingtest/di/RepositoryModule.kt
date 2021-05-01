package com.bentley.codingtest.di

import com.bentley.codingtest.data.ApiService
import com.bentley.codingtest.data.RankRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideMainRepository(apiService: ApiService): RankRepository {
        return RankRepository(apiService)
    }
}