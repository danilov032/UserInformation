package com.example.userinformation.data.utils

import com.example.userinformation.data.modeles.UserDBModel
import com.example.userinformation.data.responses.UserResponse
import com.example.userinformation.domain.modeles.User
import java.util.stream.Collectors

//fun UserResponse.mapToUser(): User =
//    with(this) {
//        User(
//            id,
//            guid.orEmpty(),
//            isActive,
//            balance.orEmpty(),
//            age,
//            eyeColor.orEmpty(),
//            name.orEmpty(),
//            gender.orEmpty(),
//            company.orEmpty(),
//            email.orEmpty(),
//            phone.orEmpty(),
//            address.orEmpty(),
//            about.orEmpty(),
//            registered.orEmpty(),
//            latitude.orEmpty(),
//            longitude.orEmpty(),
//            tags.orEmpty(),
//            friends?.map {
//                it.id
//            }.orEmpty(),
//            favoriteFruit.orEmpty()
//        )
//    }

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
            registered = registered.orEmpty(),
            latitude = latitude.orEmpty(),
            longitude = longitude.orEmpty(),
            tags = tags?.mapToString().orEmpty(),
            friends = friends?.map {
                it.id
            }?.mapToStringFromInt().orEmpty(),
            favoriteFruit = favoriteFruit.orEmpty()
        )
    }

//fun UserDBModel.mapToUserResponse(): UserResponse =
//    with(this) {
//        UserResponse(
//            id,
//            guid,
//            isActive,
//            balance,
//            age,
//            eyeColor,
//            name,
//            gender,
//            company,
//            email,
//            phone,
//            address,
//            about,
//            registered,
//            latitude,
//            longitude,
//            tags.mapToListStringFromString(),
//            friends.mapToListInt().map { friend ->
//                FriendResponse(friend)
//            },
//            favoriteFruit
//        )
//    }

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