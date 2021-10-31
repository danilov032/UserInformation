package com.example.userinformation.data.repositories

import com.example.userinformation.data.api.ApiService
import com.example.userinformation.data.datastore.UsersDataStore
import com.example.userinformation.data.mappers.UserMapper
import com.example.userinformation.data.responses.UserResponse
import com.example.userinformation.domain.modeles.User
import io.reactivex.Maybe
import io.reactivex.Single

class UserRepository(
    private val apiService: ApiService,
    private var userMapper: UserMapper,
    private val usersDataStore: UsersDataStore
) {
    fun getListUsers(): Single<List<User>> =
        usersDataStore.getListCats()
            .switchIfEmpty(
                apiService.getListUsers()
                    .map { responseList ->
                        responseList.map { response ->
                            userMapper(response)
                        }
                    }
                    .doOnSuccess { cats ->
                        usersDataStore.updateListCats(cats)
                    }
            )

    fun getCurrentUser(id: Int): Single<User> =
        getListUsers()
            .map { listUsers ->
                listUsers.find { user ->
                    user.id == id
                } ?: throw Throwable("Пользователь не найден")
            }
}