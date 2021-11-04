package com.example.userinformation.data.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.userinformation.data.modeles.UserDBModel
import com.example.userinformation.data.responses.UserResponse
import com.example.userinformation.domain.models.User
import java.util.stream.Collectors
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun UserResponse.mapToUserDBModel(): UserDBModel =
    with(this) {
        UserDBModel(
            id = id,
            guid = guid.orEmpty(),
            isActive = isActive,
            balance = balance.orEmpty(),
            age = age,
            eyeColor = eyeColor.orEmpty(),
            name = name.orEmpty(),
            gender = gender.orEmpty(),
            company = company.orEmpty(),
            email = email.orEmpty(),
            phone = phone.orEmpty(),
            address = address.orEmpty(),
            about = about.orEmpty(),
            registered = registered?.mapToData().orEmpty(),
            latitude = latitude.orEmpty(),
            longitude = longitude.orEmpty(),
            tags = tags?.mapToString().orEmpty(),
            friends = friends?.map {
                it.id
            }?.mapToStringFromInt().orEmpty(),
            favoriteFruit = favoriteFruit.orEmpty()
        )
    }

@RequiresApi(Build.VERSION_CODES.O)
fun UserDBModel.mapToUser(): User =
    with(this) {
        User(
            id,
            guid,
            isActive,
            balance,
            age,
            eyeColor,
            name,
            gender,
            company,
            email,
            phone,
            address,
            about,
            registered,
            latitude,
            longitude,
            tags.mapToListStringFromString(),
            friends.mapToListInt(),
            favoriteFruit
        )
    }

fun List<String>.mapToString(): String =
    this.stream().collect(Collectors.joining(","))

fun String.mapToListStringFromString(): List<String> =
    this.split(",").toList()

fun List<Int>.mapToStringFromInt(): String =
    this.map { it.toString() }.stream().collect(Collectors.joining(","))

fun String.mapToListInt(): List<Int> =
    this.split(",").toTypedArray().map { it.toInt() }

@RequiresApi(Build.VERSION_CODES.O)
fun String.mapToData() : String {
    val strData = this.split(" ")[0]
    val localDateTime = LocalDateTime.parse(strData)
    val formatter = DateTimeFormatter.ofPattern("HH:mm dd.MM.yy")
    return formatter.format(localDateTime)
}
