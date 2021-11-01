package com.example.userinformation.data.modeles

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.stream.Collectors

@Entity
class UserDBModel (
    @PrimaryKey(autoGenerate = true) var  idElement: Int? = null,
    val id: Int,
    val guid: String,
    val isActive: Boolean,
    val balance: String,
    val age: Int,
    val eyeColor: String,
    val name: String,
    val gender: String,
    val company: String,
    val email: String,
    val phone: String,
    val address: String,
    val about: String,
    val registered: String,
    val latitude: String,
    val longitude: String,
    val tags: String,
    val friends: String,
    val favoriteFruit: String
    )

//class TagsConverter {
//    @TypeConverter
//    fun fromTags(tags: List<String?>): String {
//        return tags.stream().collect(Collectors.joining(","))
//    }
//
//    @TypeConverter
//    fun toTags(data: String): Array<String> {
//        return data.split(",").toTypedArray()
//    }
//}
//
//class FriendsConverter {
//    @TypeConverter
//    fun fromFriends(friends: Array<Int?>): String {
//        return friends.map{it.toString()}.stream().collect(Collectors.joining(","))
//    }
//
//    @TypeConverter
//    fun toFriends(data: String): List<Int> {
//        return data.split(",").toTypedArray().map { it.toInt() }
//    }
//}