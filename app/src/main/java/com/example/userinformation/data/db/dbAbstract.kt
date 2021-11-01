package com.example.userinformation.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.userinformation.data.modeles.UserDBModel

@Database(entities = [UserDBModel::class],version = 1)
abstract class dbAbstract: RoomDatabase(){
    abstract fun catsDao(): UsersDao

    companion object {
        fun getDatabase(context: Context): dbAbstract {
            return  Room.databaseBuilder(
                context.applicationContext,
                dbAbstract::class.java,
                "myDB"
            ).build()
        }
    }
}