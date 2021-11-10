package com.example.userinformation.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.userinformation.data.modeles.UserDBModel
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UsersDao {

    @Query("SELECT * FROM UserDBModel")
    fun getUsers(): Single<List<UserDBModel>>

    @Query("SELECT * FROM UserDBModel Where id = :idUser")
    fun getCurrentUser(idUser: Int): Single<UserDBModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(users: List<UserDBModel>)

    @Query("DELETE FROM UserDBModel")
    fun deleteAll(): Completable
}