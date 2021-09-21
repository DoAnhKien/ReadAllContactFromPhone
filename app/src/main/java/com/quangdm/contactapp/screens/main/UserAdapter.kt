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
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.quangdm.contactapp.R
import com.quangdm.contactapp.utils.Utils


class UserAdapter(
    var items: MutableList<User>?, private val onClick: OnItemUserOnClick
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

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
            holder.showHideTheLayout(position, holder)

        }
        holder.itemView.setOnLongClickListener {
            onClick.onLongClick(position, items!![position])
            holder.showHideTheLayout(position, holder)
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

        fun showHideTheLayout(position: Int, holder: ViewHolder) {
            if (items!![position].isExpanded) {
                binding.llDetail.visibility = View.GONE
                Utils.collapse(binding.llDetail, 1000, 120)
                items!![position].isExpanded = false
                notifyItemChanged(position)
                return
            }
            binding.llDetail.visibility = View.VISIBLE
            Utils.expand(binding.llDetail, 1000, 120)
            items!![position].isExpanded = true
            notifyItemChanged(position)
        }
    }

    fun getUserAt(position: Int): User {
        return items!![position]
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(newItems: MutableList<User>) {
        this.items = newItems
        notifyDataSetChanged()
    }


}