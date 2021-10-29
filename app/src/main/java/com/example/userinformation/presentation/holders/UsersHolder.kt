package com.example.userinformation.presentation.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.userinformation.domain.modeles.User
import kotlinx.android.synthetic.main.user_item.view.*

class UsersHolder(
    private val view: View,
    private val containerUserListener: (User) -> Unit
) : RecyclerView.ViewHolder(view) {
    fun bindItems(user: User) {
        view.nameUser.text = user.name
        view.emailUser.text = user.email

        view.userContainer.setOnClickListener {
            containerUserListener(user)
        }
    }
}