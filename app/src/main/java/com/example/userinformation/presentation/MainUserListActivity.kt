package com.example.userinformation.presentation

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.userinformation.R
import com.example.userinformation.di.AppModule
import com.example.userinformation.di.DaggerAppComponent
import com.example.userinformation.presentation.list_users.MainUserListFragment
import moxy.MvpAppCompatActivity

class MainUserListActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_user_list)
        DaggerAppComponent.builder()
            .appModule(AppModule(application))
            .build()
            .injectMainUserListActivity(this)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, MainUserListFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
    }
}