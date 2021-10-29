package com.example.userinformation.di

import com.example.userinformation.presentation.info_user.AllInformationUserActivity
import com.example.userinformation.presentation.list_users.MainUserListActivity
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun injectMainUserListActivity(activity: MainUserListActivity)
    fun injectAllInformationUserActivity(activity: AllInformationUserActivity)
}