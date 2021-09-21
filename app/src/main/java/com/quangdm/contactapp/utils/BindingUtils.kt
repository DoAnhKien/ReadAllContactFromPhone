package com.quangdm.contactapp.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.quangdm.contactapp.model.User


object BindingUtils {

    @BindingAdapter("setTextForUserName")
    @JvmStatic
    fun setTextForUserName(tv: TextView, user: User?) {
        tv.text = user?.userName
    }

    @BindingAdapter("setTextForUserPhone")
    @JvmStatic
    fun setTextForUserPhone(tv: TextView, user: User?) {
        tv.text = user?.userPhoneNumber
    }


}