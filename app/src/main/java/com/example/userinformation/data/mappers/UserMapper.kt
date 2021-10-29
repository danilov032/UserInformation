package com.example.userinformation.data.mappers

import com.example.userinformation.data.responses.UserResponse
import com.example.userinformation.domain.modeles.User

class UserMapper {
    operator fun invoke(userResponse: UserResponse): User =
        with(userResponse) {
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
                longitude
            )
        }
}