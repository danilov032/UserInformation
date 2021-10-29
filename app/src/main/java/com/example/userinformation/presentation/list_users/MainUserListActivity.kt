package com.example.userinformation.presentation.list_users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.userinformation.R
import com.example.userinformation.data.responses.UserResponse
import com.example.userinformation.di.AppModule
import com.example.userinformation.di.DaggerAppComponent
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class MainUserListActivity : MvpAppCompatActivity(),
    MainUserContractView {

    @Inject
    lateinit var presenterLazy: dagger.Lazy<MainUserListPresenter>

    @InjectPresenter
    lateinit var presenter: MainUserListPresenter

    @ProvidePresenter
    fun providePresenter(): MainUserListPresenter {
        DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()
            .injectMainUserListActivity(this)

        return presenterLazy.get()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_user_list)
    }

    override fun showUsers(listUsers: List<UserResponse>) {

    }
}