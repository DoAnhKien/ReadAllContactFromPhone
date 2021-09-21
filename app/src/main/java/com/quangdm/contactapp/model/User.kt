package com.quangdm.contactapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val userId: String = "",
    val userName: String = "",
    val userPhoneNumber: String = "",
    var isExpanded: Boolean = false
) : Parcelable {
    constructor() : this(
        "", "", ""
    )
}