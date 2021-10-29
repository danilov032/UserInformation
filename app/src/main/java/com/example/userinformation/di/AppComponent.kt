package com.example.userinformation.di

import com.example.userinformation.presentation.info_user.AllInformationUserActivity
import com.example.userinformation.presentation.MainUserListActivity
import com.example.userinformation.presentation.list_users.MainUserListFragment
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun injectMainUserListActivity(activity: MainUserListActivity)

    fun injectMainUserListFragment(fragment: MainUserListFragment)

    fun injectAllInformationUserActivity(activity: AllInformationUserActivity)
}