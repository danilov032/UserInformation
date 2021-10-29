package com.example.userinformation.presentation.info_user

import com.example.userinformation.domain.modeles.User
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface AllInformationUserContractView : MvpView{
    fun showInfoUser(user: User)
}