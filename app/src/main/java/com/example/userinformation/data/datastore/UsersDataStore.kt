package com.example.userinformation.data.datastore

import com.example.userinformation.domain.modeles.User
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class UsersDataStore @Inject constructor() {
    private var users: List<User>? = null

    fun updateListCats(listCats: List<User>) {
        users = listCats
    }

    fun getListCats(): Maybe<List<User>> =
        users?.let { users -> Maybe.just(users) } ?: Maybe.empty()

    fun clear() {
        users = null
    }
}