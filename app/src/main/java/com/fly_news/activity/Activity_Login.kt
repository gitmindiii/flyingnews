package com.fly_news.activity

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.fly_news.R
import com.fly_news.models.SignUpResponceModel
import com.fly_news.models.UserDetailBean
import com.fly_news.server_utils.Responce_Server
import com.fly_news.server_utils.Server_Call
import com.fly_news.utilities.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

class Activity_Login : AppCompatActivity(), View.OnClickListener {

    lateinit var context: Context;
    lateinit var mProgressDialog: Dialog;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_login)
        context = this;
        isRember();
        mProgressDialog = ProgressDialog_Utils(context)
        btn_signin.setOnClickListener(this);
        tv_signup.setOnClickListener(this);
        iv_cBox.setOnClickListener(this);
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_signin ->signInTask()
            R.id.tv_signup -> {
                Common_Views.switchToActivity("Activity_Signup", context)
                finish()
              }
            R.id.iv_cBox -> checkBoxSelector()
        }
    }


    fun signInTask(){
        val isValideEmail: String = util_validation.isEmailValid(context, et_email1.text.toString().trim())
        val isValidepass: String = util_validation.isPassValid(context, et_pass.text.toString().trim())

        if (!isValideEmail.equals("done")) {
            et_email1.requestFocus()
            Common_Views.snackBar(login_layout, isValideEmail)
            return
        } else if (!isValidepass.equals("done")) {
            et_pass.requestFocus()
            Common_Views.snackBar(login_layout, isValidepass)
            return
        }

        mProgressDialog.show()

        val inputMap = mapOf("email" to et_email1.text.toString().trim(),
                "password" to et_pass.text.toString().trim(),
                "fullName" to "", "cpassword" to "", "socialId" to "", "socialType" to "")

        Server_Call.callPost(inputMap, object : Responce_Server {
            override fun fail(msg: String) {
                mProgressDialog.dismiss()
               toast(msg)

            }

            override fun success(msg: String) {
                val responce: SignUpResponceModel = Gson().fromJson<SignUpResponceModel>(msg, SignUpResponceModel::class.java!!)
                addDataToPreference(responce)
                mProgressDialog.dismiss()
                toast(responce.message)
                Common_Views.switchToActivity("Actitivty_Main",context)
                finish()
            }

        }, Constants.LOGIN_API,"fdsgdfhdgh")
    }

    fun addDataToPreference(responce: SignUpResponceModel){
         var prf=SharedPreferenceManager(context);
         prf.addString(SharedPreferenceManager.USER_NAME,responce.userDetail.fullName)
         prf.addString(SharedPreferenceManager.USER_EMAIL,responce.userDetail.email)
         prf.addString(SharedPreferenceManager.AUTH_TKOEN,responce.userDetail.authToken)
         if (isremember) prf.addBool(SharedPreferenceManager.REMEMBER_AUTH,isremember)
    }

    //***************checkBox for remeber password***********//
    var isremember:Boolean=false
    private fun checkBoxSelector() {
        var prf=SharedPreferenceManager(context);
        if (!isremember) {
           iv_cBox.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_active_checkbox))
            isremember = true
            prf.addBool(SharedPreferenceManager.REMEMBER_AUTH,isremember)
        } else {
            iv_cBox.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_inacive_checkbox))
            isremember = false
            prf.addBool(SharedPreferenceManager.REMEMBER_AUTH,isremember)
        }


    }

   fun isRember(){
       var prf=SharedPreferenceManager(context);
       isremember= prf.getBool(SharedPreferenceManager.REMEMBER_AUTH)
       if(isremember){
           iv_cBox.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_active_checkbox))
           var email:String=prf.getString(SharedPreferenceManager.USER_EMAIL)
           et_email1.setText(email)
       }

       else{
           iv_cBox.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_inacive_checkbox))

       }
   }
}
