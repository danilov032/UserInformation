package com.example.userinformation.di

import com.example.userinformation.data.api.ApiService
import com.example.userinformation.data.datastore.UsersDataStore
import com.example.userinformation.data.mappers.UserMapper
import com.example.userinformation.data.repositories.UserRepository
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideUserRepository(): UserRepository {
        return UserRepository(apiService, mapper, dataStore)
    }
    @get:Provides
    val apiService = ApiService.create()

    @get:Provides
    val mapper = UserMapper()

    @get:Provides
    val dataStore = UsersDataStore()
}