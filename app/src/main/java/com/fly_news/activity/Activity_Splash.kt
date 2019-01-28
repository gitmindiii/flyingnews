package com.fly_news.activity


import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import com.fly_news.R
import com.fly_news.utilities.Common_Views



class Activity_Splash : AppCompatActivity() {

    private val mHandlers = Handler()
    private var mRunnable: Runnable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // to hide titlebar and show srceen on full
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity__splash)
        initDelays()
    }

    fun initDelays() {
        mRunnable = Runnable {
            Common_Views.switchToActivity("Activity_Login",this)
            finish()
        }

        mHandlers.postDelayed(mRunnable, 5000)
    }

    override fun onPause() {
        super.onPause()
        mHandlers.removeCallbacks(mRunnable)
    }


}
