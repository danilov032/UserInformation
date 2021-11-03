package com.example.userinformation.presentation.holders

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.userinformation.domain.modeles.CellUserInfo
import kotlinx.android.synthetic.main.user_item.view.*

class UsersHolder(
    private val view: View,
    private val containerUserListener: (CellUserInfo) -> Unit
) : RecyclerView.ViewHolder(view) {
    fun bindItems(user: CellUserInfo) {
        view.nameUser.text = user.name
        view.emailUser.text = user.email

        if (user.isActive) {
            view.tv_active.text = "Aктивный"
            view.tv_active.setTextColor(Color.parseColor("#288507"))
        }
        else{
            view.tv_active.text = "Неактивный"
            view.tv_active.setTextColor(Color.parseColor("#9F4E524D"))
        }

        view.userContainer.setOnClickListener {
            containerUserListener(user)
        }
    }
}