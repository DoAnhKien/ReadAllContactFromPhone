package com.quangdm.contactapp.screens.main

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.quangdm.contactapp.model.User
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainPresenter(private val context: Context, private val mainInterface: MainInterface) {

    private var listData = arrayListOf<User>()

    private fun getFakeData() {
        listData.add(User("1", "KienDA1", "123456", false))
        listData.add(User("2", "KienDA2", "123456", false))
        listData.add(User("3", "KienDA3", "123456", false))
        listData.add(User("4", "KienDA4", "123456", false))
        listData.add(User("5", "KienDA5", "123456", false))
        listData.add(User("6", "KienDA6", "123456", false))
    }


    fun getListData(): MutableList<User> {
        return listData
    }

    @DelicateCoroutinesApi
    @SuppressLint("Range")
    fun readContact() {
        getFakeData()
        GlobalScope.launch {
//            val listData = mutableListOf<User>()
//            val uri: Uri = ContactsContract.Contacts.CONTENT_URI
//            val sort: String = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
//            val cursor = context.contentResolver.query(uri, null, null, null, sort)
//            if (cursor?.count!! > 0) {
//                while (cursor.moveToNext()) {
//                    val userId =
//                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
//                    val userName =
//                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
//                    val uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
//                    val selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?"
//                    val phoneCursor =
//                        context.contentResolver.query(
//                            uriPhone,
//                            null,
//                            selection,
//                            arrayOf(userId),
//                            null
//                        )
//                    if (phoneCursor?.moveToNext()!!) {
//                        val phoneNumber =
//                            phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
//                        val user = User(userId, userName, phoneNumber, false)
//                        listData.add(user)
//                    }
//
//                }
//            }
            mainInterface.getDataFromPhone(listData)
        }
    }
    fun addContactInToDatabase() {
        var count = 7
        count++
        listData.add(User(count.toString(), "KienDA${count}", "123456", false))
        Log.d(TAG, "addContactInToDatabase: ${listData.size}")
    }

    fun editUserInDatabase(user: User, position: Int) {
        listData[position] = user
    }


    fun deleteUserInDatabase(user: User) {
        listData.remove(user)
        Log.d(TAG, "deleteUserInDatabase: ${listData.size}")
    }

    companion object {
        private const val TAG = "KienDA"
    }
}