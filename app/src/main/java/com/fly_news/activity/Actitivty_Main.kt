package com.fly_news.activity

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.fly_news.R
import com.fly_news.fragments.*
import com.fly_news.utilities.Common_Views
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.header_view.*


class Actitivty_Main : AppCompatActivity() {
    lateinit var context: Context
    lateinit var menu: Menu;
    var selMenuItem: MenuItem? = null;
    val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.home_ll -> {
                tv_heading.setText(resources.getString(R.string.home_frag))
                val fragment = Home_Fragment()
                addFragment(fragment)
                setItemStyle(item)
                return@OnNavigationItemSelectedListener true
            }
            R.id.menus_ll -> {
                tv_heading.setText(resources.getString(R.string.menus_frag))
                val fragment = Menus_Frafment()
                addFragment(fragment)
                setItemStyle(item)
                return@OnNavigationItemSelectedListener true
            }
            R.id.topics_ll -> {
                tv_heading.setText(resources.getString(R.string.topics_frag))
                val fragment = Topics_Frafment()
                addFragment(fragment)
                setItemStyle(item)
                return@OnNavigationItemSelectedListener true
            }

            R.id.search_ll -> {
                tv_heading.setText(resources.getString(R.string.search_frag))
                val fragment = Search_Frafment()
                addFragment(fragment)
                setItemStyle(item)
                return@OnNavigationItemSelectedListener true
            }

            R.id.setting_ll -> {
                tv_heading.setText(resources.getString(R.string.conf_frag))
                val fragment = Configuration_Fragment()
                addFragment(fragment)
                setItemStyle(item)
                return@OnNavigationItemSelectedListener true
            }
        }
        false

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_main)
        context = this;

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        selMenuItem = navigation.menu.findItem(R.id.home_ll);
        val fragment = Home_Fragment()
        addFragment(fragment)

        btn_logout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Common_Views.logout(context)
                finish()
            }
        })

    }


    fun setItemStyle(item: MenuItem) {
        if (selMenuItem != null) {
            when (selMenuItem!!.itemId) {
                R.id.home_ll -> selMenuItem!!.icon = ContextCompat.getDrawable(context, R.drawable.ic_inactive_home)
                R.id.menus_ll -> selMenuItem!!.icon = ContextCompat.getDrawable(context, R.drawable.ic_inactive_menus_ico)
                R.id.topics_ll -> selMenuItem!!.icon = ContextCompat.getDrawable(context, R.drawable.ic_inactive_topic_ico)
                R.id.search_ll -> selMenuItem!!.icon = ContextCompat.getDrawable(context, R.drawable.ic_inactive_search_ico)
                R.id.setting_ll -> selMenuItem!!.icon = ContextCompat.getDrawable(context, R.drawable.ic_inactive_setting_ico)
            }
        }

        selMenuItem = item

        when (item.itemId) {
            R.id.home_ll -> item.icon = ContextCompat.getDrawable(context, R.drawable.ic_active_home)
            R.id.menus_ll -> item.icon = ContextCompat.getDrawable(context, R.drawable.ic_active_menus)
            R.id.topics_ll -> item.icon = ContextCompat.getDrawable(context, R.drawable.ic_active_topic_ico)
            R.id.search_ll -> item.icon = ContextCompat.getDrawable(context, R.drawable.ic_search_ico_active)
            R.id.setting_ll -> item.icon = ContextCompat.getDrawable(context, R.drawable.ic_active_setting)
        }
    }

    private fun addFragment(fragment: Fragment) {

        var currentVisibleFragment=supportFragmentManager.findFragmentByTag(fragment.javaClass.getSimpleName())
        if(currentVisibleFragment==null) {
            supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                    .replace(R.id.fl_container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
        }
    }


}