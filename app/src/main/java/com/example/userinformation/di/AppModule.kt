package com.example.userinformation.di

import com.example.userinformation.data.api.ApiService
import com.example.userinformation.data.repositories.UserRepository
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideUserRepository(): UserRepository {
        return UserRepository(apiService)
    }
    @get:Provides
    val apiService = ApiService.create()
}