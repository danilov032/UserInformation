package com.example.userinformation.presentation.list_users

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userinformation.R
import com.example.userinformation.di.AppModule
import com.example.userinformation.di.DaggerAppComponent
import com.example.userinformation.domain.modeles.User
import com.example.userinformation.presentation.adapters.UsersAdapter
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_main_user_list.*

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

    private val customAdapter: UsersAdapter by lazy {
        UsersAdapter { user ->
            presenter.onClickUser(
                user
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_user_list)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainUserListActivity)
            adapter = customAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_user_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorites) {
            Log.d("AAA", "refresh")
        }
        return true
    }

    override fun showUsers(listUsers: List<User>) {
        Log.d("AAA", listUsers.size.toString())

        customAdapter.updateItems(listUsers)
    }

    override fun showError(messageError: String) {
        Toast.makeText(applicationContext, messageError, Toast.LENGTH_SHORT).show()
    }

    override fun showDetailedInformationAboutUser(user: User) {
        Log.d("AAA", "showDetailedInformationAboutUser")
    }
}