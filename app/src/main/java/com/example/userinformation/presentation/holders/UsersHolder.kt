package com.example.userinformation.presentation.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.userinformation.domain.modeles.CellUserInfo
import com.example.userinformation.domain.modeles.User
import kotlinx.android.synthetic.main.user_item.view.*

class UsersHolder(
    private val view: View,
    private val containerUserListener: (CellUserInfo) -> Unit
) : RecyclerView.ViewHolder(view) {
    fun bindItems(user: CellUserInfo) {
        view.nameUser.text = user.name
        view.emailUser.text = user.email

        if (user.isActive) view.image_active.visibility = View.VISIBLE
        else view.image_active.visibility = View.INVISIBLE

        view.userContainer.setOnClickListener {
            containerUserListener(user)
        }
    }
}