package com.example.userinformation.presentation.info_user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.userinformation.R
import com.example.userinformation.di.AppModule
import com.example.userinformation.di.DaggerAppComponent
import com.example.userinformation.domain.modeles.User
import com.example.userinformation.presentation.list_users.MainUserContractView
import com.example.userinformation.presentation.list_users.MainUserListPresenter
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class AllInformationUserActivity : AppCompatActivity(), AllInformationUserContractView {

    @Inject
    lateinit var presenterLazy: dagger.Lazy<AllInformationUserPresenter>

    @InjectPresenter
    lateinit var presenter: AllInformationUserPresenter

    @ProvidePresenter
    fun providePresenter(): AllInformationUserPresenter {
        DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()
            .injectAllInformationUserActivity(this)

        return presenterLazy.get()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_information_user)
    }

    override fun showInfoUser(user: User) {

    }
}