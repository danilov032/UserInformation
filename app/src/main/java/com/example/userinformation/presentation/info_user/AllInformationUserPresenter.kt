package com.example.userinformation.presentation.info_user

import com.example.userinformation.domain.interactors.UserInteractor
import com.example.userinformation.domain.models.CellUserInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class AllInformationUserPresenter @Inject constructor(
    private val interactor: UserInteractor
) : MvpPresenter<AllInformationUserContractView>() {
    private val disposables = CompositeDisposable()

    fun onStartFragment(id: Int) {
        disposables.add(
            interactor.getCurrentUser(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ user ->
                    viewState.showInfoUser(user)
                    getListFriends(user.friends)
                }, {
                    viewState.showToast(it.message)
                })
        )
    }

    fun onClickUser(user: CellUserInfo) {
        if (user.isActive) viewState.showDetailedInformationAboutFriendUser(user.id)
        else viewState.showToast("Информация о данном пользователе не можетбыть просмотрена")
    }

    private fun getListFriends(listFriends: List<Int>) {
        disposables.add(
            interactor.getListUserFriends(listFriends)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ friends ->
                    viewState.showFriends(friends)
                }, {
                    viewState.showToast(it.message)
                })
        )
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}