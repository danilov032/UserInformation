package com.example.userinformation.presentation.list_users

import com.example.userinformation.domain.modeles.User
import com.example.userinformation.presentation.BaseContractView
import moxy.MvpView
import moxy.viewstate.strategy.*

@StateStrategyType(SkipStrategy::class)
interface MainUserContractView: MvpView, BaseContractView {
    fun showUsers(listUsers: List<User>)

    fun showDetailedInformationAboutUser(id: Int)
}