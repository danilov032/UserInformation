package com.example.userinformation.presentation.info_user

import com.example.userinformation.domain.modeles.User
import com.example.userinformation.presentation.BaseContractView
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SkipStrategy::class)
interface AllInformationUserContractView : MvpView, BaseContractView {
    fun showInfoUser(user: User)
}