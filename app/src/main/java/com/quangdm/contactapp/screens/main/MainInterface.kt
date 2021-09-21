package com.quangdm.contactapp.screens.main

import com.quangdm.contactapp.model.User

interface MainInterface {
    fun getDataFromPhone(mData : MutableList<User>)
}