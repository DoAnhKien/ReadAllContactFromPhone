package com.quangdm.contactapp.screens.addedit
import android.view.View
import com.quangdm.contactapp.R
import com.quangdm.contactapp.base.BaseActivity
import com.quangdm.contactapp.databinding.ActivityAddEditBinding

class AddEditActivity : BaseActivity<ActivityAddEditBinding>(), View.OnClickListener,
    AddEdiInterface {
//
//    private var user: User? = null

    override fun initLayout(): Int = R.layout.activity_add_edit

    override fun init() {
        initDataForActivity()
    }

    private fun initDataForActivity() {
//        val user = intent.getBundleExtra("1234")?.getParcelable<User>("123")
//        if (user != null) {
//            binding?.user = user
//            return
//        }
    }

    override fun setOnClickForViews() {
        binding?.tvCancel?.setOnClickListener(this)
        binding?.tvConfirm?.setOnClickListener(this)
    }

    override fun initViews() {

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.tvCancel -> {

            }
            R.id.tvConfirm -> {

            }
        }
    }

}