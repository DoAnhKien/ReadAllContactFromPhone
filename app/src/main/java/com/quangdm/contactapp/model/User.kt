package com.quangdm.contactapp.model

data class User(
    var userId: String = "",
    var userName: String = "",
    var userPhoneNumber: String = "",
    var isExpanded: Boolean = false
)