package com.example.userinformation.domain.interactors

import com.example.userinformation.data.repositories.UserRepository
import com.example.userinformation.domain.modeles.CellUserInfo
import com.example.userinformation.domain.modeles.User
import io.reactivex.Single
import javax.inject.Inject

class UserInteractor @Inject constructor(private val userRepository: UserRepository) {

    fun getCurrentUser(id: Int): Single<User> = userRepository.getCurrentUser(id)

    fun getListUserFriends(listFriends: List<Int>): Single<List<CellUserInfo>> = userRepository.getListUserFriends(listFriends)

    fun getCountUsers(): Single<Int> = userRepository.getCountUsers()

    fun getUsersFromBD(): Single<List<CellUserInfo>> = userRepository.getFullInformationAboutUserFromBD()

    fun getUsersFromServer():Single<List<CellUserInfo>> = userRepository.getUsersFromServer()
}