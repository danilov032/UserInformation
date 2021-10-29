package com.example.userinformation.presentation.list_users

import com.example.userinformation.data.responses.UserResponse
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainUserContractView: MvpView {
    fun showUsers(listUsers: List<UserResponse>)
}