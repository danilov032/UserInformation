package com.example.userinformation.data.repositories

import com.example.userinformation.data.api.ApiService
import com.example.userinformation.data.mappers.UserMapper
import com.example.userinformation.data.responses.UserResponse
import com.example.userinformation.domain.modeles.User
import io.reactivex.Single

class UserRepository(private val apiService: ApiService, private var userMapper: UserMapper) {
    fun getListUsers(): Single<List<User>> =
        apiService.getListUsers().map { listUsers ->
            listUsers.map { user ->
                userMapper(user)
            }
        }
}