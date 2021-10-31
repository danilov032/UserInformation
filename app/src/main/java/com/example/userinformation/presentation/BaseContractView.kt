package com.example.userinformation.presentation

import moxy.MvpView

interface BaseContractView: MvpView {
    fun showError(messageError: String)
}