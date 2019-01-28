package com.fly_news.activity

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.fly_news.ImagePickerPackge.ImagePicker
import com.fly_news.ImagePickerPackge.StoreImage
import com.fly_news.R
import com.fly_news.models.SignUpResponceModel
import com.fly_news.server_utils.Responce_Server
import com.fly_news.server_utils.Server_Call
import com.fly_news.utilities.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_signup.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import java.io.File


class Activity_Signup : AppCompatActivity(), View.OnClickListener {

    lateinit var context: Context;
    lateinit var imagePicker_View: ImagePicker_View;
    lateinit var mProgressDialog: Dialog;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_signup)
        context = this;

        mProgressDialog = ProgressDialog_Utils(context)

        if (!checkPermission()) {
            requestPermission()
        }

        fl_profile.setOnClickListener(this)
        btn_signup.setOnClickListener(this)
        ll_signin.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_signup -> {
                val userName_valid: String = util_validation.isNameValidStr(context, et_full_name.text.toString().trim())
                val email_valid: String = util_validation.isEmailValid(context, et_email_signup.text.toString().trim())
                val pass_valid: String = util_validation.isPassValid(context, et_pass_signup.text.toString().trim())
                val confirpass_valid: String = util_validation.isPassValid(context, et_confirm_pass_signup.text.toString().trim())

                if (path == null) {
                    Common_Views.snackBar(main_layout, context.getString(R.string.pick_image_intent_text))
                    return
                } else if (!userName_valid.equals("done")) {
                    et_full_name.requestFocus()
                    Common_Views.snackBar(main_layout, userName_valid)
                    return
                } else if (!email_valid.equals("done")) {
                    et_email_signup.requestFocus()
                    Common_Views.snackBar(main_layout, email_valid)
                    return
                } else if (!pass_valid.equals("done")) {
                    et_pass_signup.requestFocus()
                    Common_Views.snackBar(main_layout, pass_valid)
                    return
                } else if (!et_confirm_pass_signup.text.toString().trim().equals(et_pass_signup.text.toString().trim())) {
                    et_confirm_pass_signup.requestFocus()
                    Common_Views.snackBar(main_layout, context.getString(R.string.the_confirm_pass_filed_does_not_match))

                    return
                }


                val inputMap = mapOf("email" to et_email_signup.text.toString().trim(),
                        "password" to et_pass_signup.text.toString().trim(),
                        "fullName" to et_full_name.text.toString().trim(),
                        "cpassword" to et_confirm_pass_signup.text.toString().trim(),
                        "socialId" to "",
                        "socialType" to "")


                mProgressDialog.show()
                Server_Call.callMultiPart(inputMap, path!!, object : Responce_Server {
                    override fun success(msg: String) {
                        val responce: SignUpResponceModel = Gson().fromJson<SignUpResponceModel>(msg, SignUpResponceModel::class.java!!)
                        addDataToPreference(responce);
                        toast(responce.message)
                        Common_Views.switchToActivity("Actitivty_Main",context)
                        finish()

                        mProgressDialog.dismiss()
                    }

                    override fun fail(msg: String) {
                        toast(msg)
                        mProgressDialog.dismiss()
                    }

                }, Constants.SIGNUP_API,"adsfsdfgdfh")

            }

            R.id.fl_profile -> {
                ImagePicker.pickImage(this)
            }

            R.id.ll_signin -> {
                Common_Views.switchToActivity("Activity_Login",context)
                finish()
            }
        }
    }

    var path: File? = null;
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            if (requestCode == 234) {

                var bitmap: Bitmap = ImagePicker.getImageFromResult(this, requestCode, resultCode, data)

                if (bitmap != null) {
                    path = StoreImage.saveImage(context, bitmap)
                    iv_profile!!.setImageBitmap(bitmap)
                }


            }
        }


    }


    internal var perms = arrayOf("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.CAMERA")
    internal var permsRequestCode = 200

    private fun checkPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val result1 = ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val result2 = ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA)

        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {

        ActivityCompat.requestPermissions(this, perms, permsRequestCode)

    }
    fun addDataToPreference(responce: SignUpResponceModel){
        var prf=SharedPreferenceManager(context);
        prf.addString(SharedPreferenceManager.USER_NAME,responce.userDetail.fullName)
        prf.addString(SharedPreferenceManager.USER_EMAIL,responce.userDetail.email)
        prf.addString(SharedPreferenceManager.AUTH_TKOEN,responce.userDetail.authToken)

    }

    override fun onBackPressed() {
        Common_Views.switchToActivity("Activity_Login",context)
        finish()
    }


}