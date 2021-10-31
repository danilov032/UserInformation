package com.example.userinformation.domain.interactors

import com.example.userinformation.data.repositories.UserRepository
import com.example.userinformation.domain.modeles.User
import io.reactivex.Single
import javax.inject.Inject

class UserInteractor @Inject constructor(private val userRepository: UserRepository) {
    fun getUserList(): Single<List<User>> = userRepository.getListUsers()

    fun getCurrentUser(id: Int): Single<User> = userRepository.getCurrentUser(id)
}