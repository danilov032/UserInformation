package com.example.userinformation.data.repositories

import com.example.userinformation.data.api.ApiService
import com.example.userinformation.data.db.UsersDao
import com.example.userinformation.data.utils.mapToUser
import com.example.userinformation.data.utils.mapToUserDBModel
import com.example.userinformation.domain.models.CellUserInfo
import com.example.userinformation.domain.models.User
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDB: UsersDao
) {
    fun getCountUsers() = userDB.getCountUsers()

    fun deleteAll(): Completable = userDB.deleteAll()

    private fun getUsersFromBD(): Single<List<User>> =
        userDB.getUsers()
            .map { responseList ->
                responseList.map { response ->
                    response.mapToUser()
                }
            }

    fun getFullInformationAboutUserFromBD(): Single<List<CellUserInfo>> =
        getUsersFromBD().map { listUsers ->
            listUsers.map { user ->
                CellUserInfo(
                    user.id,
                    user.isActive,
                    user.name,
                    user.email
                )
            }
        }

    fun getUsersFromServer(): Single<List<CellUserInfo>> =
        apiService.getListUsers()
            .map { responseList ->
                responseList.map { response ->
                    response.mapToUserDBModel()
                }
            }
            .doOnSuccess { users ->
                userDB.insertUser(users)
            }
            .map { listUsers ->
                listUsers.map { user ->
                    CellUserInfo(
                        user.id,
                        user.isActive,
                        user.name,
                        user.email
                    )
                }
            }

    fun getCurrentUser(id: Int): Single<User> =
        userDB.getUsers()
            .map { listUsers ->
                listUsers.map { user ->
                    user.mapToUser()
                }
                    .find { user ->
                        user.id == id
                    } ?: throw Throwable("Пользователь не найден")

            }

    fun getListUserFriends(listFriends: List<Int>): Single<List<CellUserInfo>> =
        userDB.getUsers()
            .map { listUsers ->
                listUsers.filter { user ->
                    listFriends.contains(user.id)
                }
                    .map { user ->
                        CellUserInfo(
                            user.id,
                            user.isActive,
                            user.name,
                            user.email
                        )
                    }
            }
}