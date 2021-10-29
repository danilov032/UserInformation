package com.example.userinformation.presentation.list_users

import com.example.userinformation.domain.interactors.UserInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class MainUserListPresenter @Inject constructor(
    private val interactor: UserInteractor
) : MvpPresenter<MainUserContractView>(){

    override fun attachView(view: MainUserContractView?) {
        super.attachView(view)

        interactor.getUserList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ listUsers ->
                viewState.showUsers(listUsers)
            }, {})
    }
}