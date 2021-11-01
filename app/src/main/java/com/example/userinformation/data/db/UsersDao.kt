package com.example.userinformation.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.userinformation.data.modeles.UserDBModel
import io.reactivex.Single

@Dao
interface UsersDao {

    @Query("SELECT * FROM UserDBModel")
    fun getUsers(): Single<List<UserDBModel>>

    @Query("SELECT count(*) FROM UserDBModel")
    fun getCountUsers(): Single<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(users: List<UserDBModel>)
}