package com.quangdm.contactapp.screens.main

import com.quangdm.contactapp.model.User

interface OnItemUserOnClick {
    fun onClick(position: Int, user: User)
    fun onLongClick(position: Int, user: User)
}