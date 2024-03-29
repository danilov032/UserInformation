package com.example.userinformation.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.userinformation.R
import com.example.userinformation.domain.models.CellUserInfo
import com.example.userinformation.presentation.holders.UsersHolder

class UsersAdapter (private val containerUserListener: (CellUserInfo) -> Unit) : RecyclerView.Adapter<UsersHolder>() {

    private val items: MutableList<CellUserInfo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false)
        return UsersHolder(view, containerUserListener)
    }

    override fun onBindViewHolder(holder: UsersHolder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(list: List<CellUserInfo>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}
