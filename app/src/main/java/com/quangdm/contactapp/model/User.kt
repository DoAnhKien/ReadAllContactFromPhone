package com.quangdm.contactapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var userId: String = "",
    var userName: String = "",
    var userPhoneNumber: String = "",
    var isExpanded: Boolean = false
) : Parcelable