package com.example.userinformation.presentation.list_users

import android.util.Log
import com.example.userinformation.domain.interactors.UserInteractor
import com.example.userinformation.domain.modeles.User
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
                Log.d("AAA", "interactor")
                viewState.showUsers(listUsers)
            }, {
                Log.d("AAA", it.message?: "")
                viewState.showError(it.message?: "Неизвестная ошибка")
            })
    }

    fun onClickUser(user: User){
        Log.d("AAA",  "userClick")
        if(user.isActive) viewState.showDetailedInformationAboutUser(user)
        else viewState.showError("Информация о данном пользователе не можетбыть просмотрена")
    }
}