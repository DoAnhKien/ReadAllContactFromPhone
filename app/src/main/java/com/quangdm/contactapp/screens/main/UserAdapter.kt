package com.quangdm.contactapp.screens.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.quangdm.contactapp.databinding.LayoutItemUserBinding
import com.quangdm.contactapp.model.User

class UserAdapter(
    var items: MutableList<User>?, private val onClick: OnItemUserOnClick
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private lateinit var layoutItemUserBinding: LayoutItemUserBinding
    private var isSelected = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int {
        if (items?.size!! > 0) {
            return items!!.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binData(items!![position], position)
        holder.itemView.setOnClickListener {
            onClick.onClick(position, items!![position])
            holder.showHideTheLayout()
        }
        holder.itemView.setOnLongClickListener {
            onClick.onLongClick(position, items!![position])
            holder.showHideTheLayout()
            true
        }

    }

    inner class ViewHolder(var binding: LayoutItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binData(user: User, position: Int) {
            binding.apply {
                binding.user = user
                executePendingBindings()
            }
        }

        fun showHideTheLayout() {
            if (isSelected) {
                binding.llDetail.visibility = View.GONE
                isSelected = false
                return
            }
            binding.llDetail.visibility = View.VISIBLE
            isSelected = true
        }
    }

    fun showDetail() {

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(newItems: MutableList<User>) {
        this.items = newItems
        notifyDataSetChanged()
    }


}