package com.fly_news.utilities

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.fly_news.R

class ProgressDialog_Utils : Dialog{

    constructor(context: Context) : super(context) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        this.setCancelable(false)
        this.setContentView(R.layout.activity_progress_dialog)
    }
}