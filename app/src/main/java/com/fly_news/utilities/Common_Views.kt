package com.fly_news.utilities

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.fly_news.R
import com.fly_news.activity.Actitivty_Main
import com.fly_news.activity.Activity_Login
import com.fly_news.activity.Activity_NewsDetail
import com.fly_news.activity.Activity_Signup
import com.google.android.material.snackbar.Snackbar

class Common_Views {

    companion object {
        fun snackBar(view: View, msg: String) {
            val snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
            val snackBarView = snackbar.getView()
            snackBarView.setBackgroundColor(view.resources.getColor(R.color.colorDarkBlack))
            val snack_text_view = snackBarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            snack_text_view.setTextColor(-0xd95401)
            snackbar.show()
        }

        fun customToast(context: Context, msg: String): Toast {
            return Toast.makeText(context, msg, Toast.LENGTH_SHORT).apply { show() }
        }


        fun switchToActivity(activity: String, context: Context) {
            var intent: Intent? = null
            when (activity) {
                "Activity_Login" -> intent = Intent(context, Activity_Login::class.java)
                "Activity_Signup" -> intent = Intent(context, Activity_Signup::class.java)
                "Actitivty_Main" -> intent = Intent(context, Actitivty_Main::class.java)
            }

            context.startActivity(intent)

        }

        fun logout(context: Context) {
            var intent = Intent(context, Activity_Login::class.java)
            context.startActivity(intent)
        }
    }


}