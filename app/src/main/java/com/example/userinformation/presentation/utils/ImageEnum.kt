package com.example.userinformation.presentation.utils

import androidx.annotation.DrawableRes
import com.example.userinformation.R

enum class ImageEnum {
    APPLE,
    BANANA,
    STRAWBERRY,
    UNKNOWN;

    @DrawableRes
    fun getResource(): Int = when (this) {
        APPLE -> R.drawable.apple
        BANANA -> R.drawable.bananas
        STRAWBERRY -> R.drawable.strawberry
        UNKNOWN -> R.drawable.strawberry
    }
    companion object{
        fun getByColorName(colorName: String): ImageEnum =
            when (colorName){
                "apple" -> APPLE
                "banana" -> BANANA
                "strawberry" -> STRAWBERRY
                else -> UNKNOWN
            }
    }
}