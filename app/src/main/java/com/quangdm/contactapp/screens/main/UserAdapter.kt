package com.quangdm.contactapp.screens.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quangdm.contactapp.databinding.LayoutItemUserBinding
import com.quangdm.contactapp.model.User
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

        holder.binData(items!![position])
        holder.itemView.setOnClickListener {
            onClick.onClick(position, items!![position])
            holder.showHideTheLayout(position)

        }
        holder.itemView.setOnLongClickListener {
            onClick.onLongClick(position, items!![position])
            holder.showHideTheLayout(position)
            true
        }

    }


    inner class ViewHolder(var binding: LayoutItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binData(user: User) {
            binding.apply {
                binding.user = user
                executePendingBindings()
            }
        }

        fun showHideTheLayout(position: Int) {
            binding.llDetail.measure(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            val height = binding.llDetail.measuredHeight

            if (items!![position].isExpanded) {
                binding.llDetail.visibility = View.GONE
                Utils.collapse(binding.llDetail, 1000, height)
                items!![position].isExpanded = false
                notifyItemChanged(position)
                return
            }
            binding.llDetail.visibility = View.VISIBLE
            Utils.expand(binding.llDetail, 1000, height)
            items!![position].isExpanded = true
            notifyItemChanged(position)
        }
    }

    fun getUserAt(position: Int): User {
        return items!![position]
    }


    fun clearData() {
        this.items?.clear()

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(newItems: MutableList<User>) {
        this.items = newItems
        notifyDataSetChanged()
    }


}