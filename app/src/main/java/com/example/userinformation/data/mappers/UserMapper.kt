package com.example.userinformation.data.mappers

import com.example.userinformation.data.responses.UserResponse
import com.example.userinformation.domain.modeles.User

class UserMapper {
    operator fun invoke(userResponse: UserResponse): User =
        with(userResponse) {
            User(
                id,
                guid.orEmpty(),
                isActive,
                balance.orEmpty(),
                age,
                eyeColor.orEmpty(),
                name.orEmpty(),
                gender.orEmpty(),
                company.orEmpty(),
                email.orEmpty(),
                phone.orEmpty(),
                address.orEmpty(),
                about.orEmpty(),
                registered.orEmpty(),
                latitude.orEmpty(),
                longitude.orEmpty(),
                tags.orEmpty(),
                friends?.map {
                    it.id
                }.orEmpty(),
                favoriteFruit.orEmpty()
            )
        }
}