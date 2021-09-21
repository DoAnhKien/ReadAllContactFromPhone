package com.quangdm.contactapp.screens.main

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import com.quangdm.contactapp.base.LoadingDialog.context
import com.quangdm.contactapp.model.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainPresenter(private val context: Context, private val mainInterface: MainInterface) {


    @SuppressLint("Range")
    fun readContact() {
        GlobalScope.launch {
            val listData = mutableListOf<User>()
            val uri: Uri = ContactsContract.Contacts.CONTENT_URI
            val sort: String = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
            val cursor = context.contentResolver.query(uri, null, null, null, sort)
            if (cursor?.count!! > 0) {
                while (cursor.moveToNext()) {
                    val userId =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                    val userName =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    val uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                    val selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?"
                    val phoneCursor =
                        context.contentResolver.query(
                            uriPhone,
                            null,
                            selection,
                            arrayOf(userId),
                            null
                        )
                    if (phoneCursor?.moveToNext()!!) {
                        val phoneNumber =
                            phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                        val user = User(userId, userName, phoneNumber, false)
                        listData.add(user)
                    }

                }
            }
            mainInterface.getDataFromPhone(listData)
        }


    }
}