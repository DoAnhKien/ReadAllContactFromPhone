package com.quangdm.contactapp.screens.main

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.pm.PackageManager
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.quangdm.contactapp.R
import com.quangdm.contactapp.base.*
import com.quangdm.contactapp.databinding.ActivityMainBinding
import com.quangdm.contactapp.model.User
import com.quangdm.contactapp.utils.Const
import kotlinx.coroutines.DelicateCoroutinesApi

class MainActivity : BaseActivity<ActivityMainBinding>(), View.OnClickListener, OnItemUserOnClick,
    MainInterface {

    private var userAdapter: UserAdapter? = null
    private var mainPresenter: MainPresenter? = null
    private lateinit var loading: LoadingDialog
    private lateinit var dialog: ShowDialog.Builder

    override fun initLayout(): Int = R.layout.activity_main

    @DelicateCoroutinesApi
    override fun init() {
        mainPresenter = MainPresenter(this, this)
        checkContactPermission()
        loading = LoadingDialog.getInstance(this)
        dialog = ShowDialog.Builder(this)
    }

    override fun setOnClickForViews() {
        binding?.flbAdd?.setOnClickListener(this)
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

    @SuppressLint("NotifyDataSetChanged")
    private fun setAnimationForRecyclerView(recyclerView: RecyclerView) {
        val context = recyclerView.context
        val layoutAnimation =
            AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fail_down_animation)
        recyclerView.layoutAnimation = layoutAnimation
        recyclerView.adapter?.notifyDataSetChanged()
        recyclerView.scheduleLayoutAnimation()
    }

    @DelicateCoroutinesApi
    private fun checkContactPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                Array(1) { android.Manifest.permission.READ_CONTACTS },
                Const.REQUEST_CODE_TO_CHECK_PERMISSION
            )
        } else {
            mainPresenter!!.readContact()
            initDataForRecyclerViews()
        }
    }

    @DelicateCoroutinesApi
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Const.REQUEST_CODE_TO_CHECK_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            mainPresenter!!.readContact()
            initDataForRecyclerViews()
        }
    }

    companion object {
        private const val TAG = "KienDA"
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.flbAdd -> {
                mainPresenter?.addContactInToDatabase()
                userAdapter?.notifyItemInserted(mainPresenter?.getListData()!!.size)
            }
        }
    }

    override fun onClick(position: Int, user: User) {
//        user.userName = "mmmmmm"
//        mainPresenter?.editUserInDatabase(user, position)
//        userAdapter?.notifyItemChanged(position)
//        val intent = Intent(this, AddEditActivity::class.java)
//        intent.apply {
//            val bundle = Bundle()
//            bundle.putParcelable("123", user)
//            intent.putExtra("1234", bundle)
//            startActivityForResult(this, Const.REQUEST_CODE_TO_START_ACTIVITY)
//        }

    }

    override fun onLongClick(position: Int, user: User) {
        var dialog: Dialog? = null
        dialog = ShowDialog.Builder(this)
            .title("Xóa tài khoản?")
            .message("Bạn có chắc chắn muốn xóa ${user.userName} khỏi danh bạn")
            .setRightButton("Bỏ qua", object : DialogRightInterface {
                override fun onClick() {
                    dialog?.dismiss()
                }
            })
            .setLeftButton("Chắc chắn", object : DialogLeftInterface {
                override fun onClick() {
                    mainPresenter?.deleteUserInDatabase(user)
                    userAdapter!!.notifyItemRemoved(position)
                    dialog?.dismiss()
                }
            })
            .confirm()
        dialog?.show()
    }



    override fun getDataFromPhone(mData: MutableList<User>) {
        runOnUiThread {
            userAdapter?.setNewData(mData)
            setAnimationForRecyclerView(binding!!.rvMain)
            Log.d(TAG, "getDataFromPhone: ${mData.size}")
        }

    }


}