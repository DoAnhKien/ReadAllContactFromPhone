package com.quangdm.contactapp.screens.main

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.quangdm.contactapp.R
import com.quangdm.contactapp.base.BaseActivity
import com.quangdm.contactapp.databinding.ActivityMainBinding
import com.quangdm.contactapp.model.User
import java.util.jar.Manifest

class MainActivity : BaseActivity<ActivityMainBinding>(), View.OnClickListener, OnItemUserOnClick,
    MainInterface {

    private var userAdapter: UserAdapter? = null
    private var mainPresenter: MainPresenter? = null
    private var listData: MutableList<User>? = null

    override fun initLayout(): Int = R.layout.activity_main

    override fun init() {
        mainPresenter = MainPresenter(this, this)
        checkContactPermission()

    }

    override fun setOnClickForViews() {

    }

    override fun initViews() {

    }

    private fun initDataForRecyclerViews() {
        val mutableList = arrayListOf<User>()
        userAdapter = UserAdapter(mutableList, this)
        binding.let {
            it?.rvMain!!.apply {
                adapter = userAdapter
                layoutManager =
                    LinearLayoutManager(
                        this@MainActivity,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                hasFixedSize()
            }
        }

    }

    private fun setAnimationForRecyclerView(recyclerView: RecyclerView) {
        val context = recyclerView.context
        val layoutAnimation =
            AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fail_down_animation)
        recyclerView.layoutAnimation = layoutAnimation
        recyclerView.adapter?.notifyDataSetChanged()
        recyclerView.scheduleLayoutAnimation()
    }

    private fun checkContactPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                Array(1) { android.Manifest.permission.READ_CONTACTS },
                111
            )
        } else {
            mainPresenter!!.readContact()
            initDataForRecyclerViews()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            mainPresenter!!.readContact()
            initDataForRecyclerViews()
        }
    }

    companion object {
        private const val TAG = "KienDA"
    }

    override fun onClick(p0: View?) {

    }

    override fun onClick(position: Int, user: User) {

    }

    override fun onLongClick(position: Int, user: User) {

    }

    override fun getDataFromPhone(mData: MutableList<User>) {
        runOnUiThread {
            userAdapter?.setNewData(mData)
            setAnimationForRecyclerView(binding!!.rvMain)
        }

    }


}