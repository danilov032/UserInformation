package com.example.userinformation.domain.interactors

import com.example.userinformation.data.repositories.UserRepository
import com.example.userinformation.data.responses.UserResponse
import io.reactivex.Single
import javax.inject.Inject

class UserInteractor @Inject constructor(private val userRepository: UserRepository) {
    fun getUserList(): Single<List<UserResponse>> = userRepository.getListUsers()
}