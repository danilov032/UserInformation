package com.example.userinformation.presentation.info_user

import com.example.userinformation.domain.interactors.UserInteractor
import com.example.userinformation.presentation.list_users.MainUserContractView
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class AllInformationUserPresenter @Inject constructor(
    private val interactor: UserInteractor
) : MvpPresenter<AllInformationUserContractView>() {

    override fun attachView(view: AllInformationUserContractView?) {
        super.attachView(view)
    }
}