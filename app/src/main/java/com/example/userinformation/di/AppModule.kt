package com.example.userinformation.di

import android.app.Application
import com.example.userinformation.data.api.ApiService
import com.example.userinformation.data.db.dbAbstract
import com.example.userinformation.data.repositories.UserRepository
import dagger.Module
import dagger.Provides

@Module
class AppModule(application: Application) {

    @Provides
    fun provideUserRepository(): UserRepository {
        return UserRepository(apiService, dataBase)
    }
    @get:Provides
    val apiService = ApiService.create()

    @get:Provides
    val dataBase = dbAbstract.getDatabase(application).catsDao()
}