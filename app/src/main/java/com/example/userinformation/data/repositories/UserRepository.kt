package com.example.userinformation.data.repositories

import com.example.userinformation.data.api.ApiService
import com.example.userinformation.data.responses.UserResponse
import io.reactivex.Single

class UserRepository (private val apiService: ApiService) {
    fun getListUsers(): Single<List<UserResponse>> = apiService.getListUsers()
}