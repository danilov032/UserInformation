package com.example.userinformation.presentation.list_users

import android.util.Log
import com.example.userinformation.domain.interactors.UserInteractor
import com.example.userinformation.domain.modeles.CellUserInfo
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
        interactor.getCountUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ count ->
                if(count == 0){
                    getDataFromServer()
                }
                else{
                    getDataFromBD()
                }
            }, {
                Log.d("AAA", it.message?: "")
                viewState.showError(it.message?: "Неизвестная ошибка")
            })
    }

    private fun getDataFromBD(){
        interactor.getUsersFromBD()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ listUsers ->
                viewState.showUsers(listUsers)
            }, {
                Log.d("AAA", it.message?: "")
                viewState.showError(it.message?: "Неизвестная ошибка")
            })
    }

    private fun getDataFromServer(){
        interactor.getUsersFromServer()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ listUsers ->
                viewState.showUsers(listUsers)
            }, {
                Log.d("AAA", it.message?: "")
                viewState.showError(it.message?: "Неизвестная ошибка")
            })
    }

    fun onClickUser(user: CellUserInfo){
        if(user.isActive) viewState.showDetailedInformationAboutUser(user.id)
        else viewState.showError("Информация о данном пользователе не можетбыть просмотрена")
    }
}