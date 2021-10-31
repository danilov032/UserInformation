package com.example.userinformation.presentation.info_user

import android.util.Log
import com.example.userinformation.domain.interactors.UserInteractor
import com.example.userinformation.domain.modeles.CellUserInfo
import com.example.userinformation.domain.modeles.User
import com.example.userinformation.presentation.list_users.MainUserContractView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class AllInformationUserPresenter @Inject constructor(
    private val interactor: UserInteractor
) : MvpPresenter<AllInformationUserContractView>() {

    fun getCurrentUser(id: Int){
        interactor.getCurrentUser(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ user ->
                viewState.showInfoUser(user)
                getListFriends(user.friends)
            }, {
                Log.d("AAA", it.message?: "")
                viewState.showError(it.message?: "Неизвестная ошибка")
            })
    }

    private fun getListFriends(listFriends: List<Int>){
        interactor.getListUserFriends(listFriends)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ friends ->
                viewState.showFriends(friends)
            }, {
                Log.d("AAA", it.message?: "")
                viewState.showError(it.message?: "Неизвестная ошибка")
            })
    }

    fun onClickUser(user: CellUserInfo){
        if(user.isActive) viewState.showDetailedInformationAboutFriendUser(user.id)
        else viewState.showError("Информация о данном пользователе не можетбыть просмотрена")
    }
}