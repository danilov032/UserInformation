package com.example.userinformation.presentation.list_users

import com.example.userinformation.domain.interactors.UserInteractor
import com.example.userinformation.domain.models.CellUserInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class MainUserListPresenter @Inject constructor(
    private val interactor: UserInteractor
) : MvpPresenter<MainUserContractView>() {

    private val disposable = CompositeDisposable()

    override fun attachView(view: MainUserContractView?) {
        super.attachView(view)
        disposable.add(
            interactor.getUsersFromBD()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ users ->
                    if (users.isEmpty()) {
                        getDataFromServer()
                    } else {
                        viewState.showUsers(users)
                    }
                }, {
                    viewState.showToast(it.message)
                })
        )
    }

    fun updateDataCache() {
        disposable.add(
            interactor.deleteAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    getDataFromServer()
                    viewState.showToast("Данные обновлены")
                }, {
                    viewState.showToast(it.message)
                })
        )
    }

    fun onClickUser(user: CellUserInfo) =
        if (user.isActive) viewState.showDetailedInformationAboutUser(user.id)
        else viewState.showToast("Информация о данном пользователе не может быть просмотрена")

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }

    private fun getDataFromServer() {
        disposable.add(
            interactor.getUsersFromServer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listUsers ->
                    viewState.showUsers(listUsers)
                }, {
                    viewState.showToast(it.message)
                })
        )
    }
}