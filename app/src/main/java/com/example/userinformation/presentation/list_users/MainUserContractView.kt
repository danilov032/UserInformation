package com.example.userinformation.presentation.list_users

import com.example.userinformation.domain.modeles.User
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainUserContractView: MvpView {
    fun showUsers(listUsers: List<User>)

    fun showError(messageError: String)

    fun showDetailedInformationAboutUser(user: User)
}