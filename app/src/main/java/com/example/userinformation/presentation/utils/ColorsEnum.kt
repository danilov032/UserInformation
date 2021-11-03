package com.example.userinformation.presentation.utils

enum class ColorsEnum {
    BLUE,
    BROWN,
    GREEN,
    UNKNOWN;

    fun getResource(): String = when (this) {
        BLUE -> "#2408f1"
        BROWN -> "#03fb25"
        GREEN -> "#5c4213"
        UNKNOWN -> "#00FFFFFF"
    }
    companion object{
        fun getByColorName(colorName: String): ColorsEnum =
            when (colorName){
                "blue" -> BLUE
                "green" -> GREEN
                "brown" -> BROWN
                else -> UNKNOWN
            }
    }
}