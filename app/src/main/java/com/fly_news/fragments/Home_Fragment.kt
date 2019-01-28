package com.fly_news.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.fly_news.R
import com.fly_news.adapter.Home_Fragment_Adapter
import com.fly_news.models.CatData
import com.fly_news.models.NewsCatModel
import com.fly_news.server_utils.Responce_Server
import com.fly_news.server_utils.Server_Call
import com.fly_news.utilities.Constants
import com.fly_news.utilities.ProgressDialog_Utils
import com.fly_news.utilities.SharedPreferenceManager
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import org.jetbrains.anko.toast


class Home_Fragment : Fragment() {
    lateinit var cat_list:List<CatData>


    lateinit var viewpager: ViewPager;
    lateinit var sliding_tabs: TabLayout;
    lateinit var mProgressDialog: Dialog;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false);
        viewpager = view.findViewById(R.id.viewpager)
        sliding_tabs = view.findViewById(R.id.sliding_tabs)
        mProgressDialog = ProgressDialog_Utils(this!!.context!!)
        getNewsCategories()

        return view;
    }


    fun initTabs(cat_list: List<CatData>) {

        val adapter = Home_Fragment_Adapter(childFragmentManager, this!!.activity!!, cat_list)
        viewpager.adapter = adapter
        sliding_tabs.setupWithViewPager(viewpager)
        sliding_tabs.addOnTabSelectedListener(TabSelectionListener())
        for (i in 0 until sliding_tabs.getTabCount()) {

            val tab = (sliding_tabs.getChildAt(0) as ViewGroup).getChildAt(i)

            val p = tab.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(0, 0, 20, 0)
            tab.requestLayout()
        }
    }

    fun getNewsCategories() {
        mProgressDialog.show()
        val authToken = SharedPreferenceManager(this!!.activity!!).getString(SharedPreferenceManager.AUTH_TKOEN)
        Server_Call.callGet(object : Responce_Server {
            override fun fail(msg: String) {
                activity!!.toast(msg)
                mProgressDialog.dismiss()
            }

            override fun success(msg: String) {
                val responce: NewsCatModel = Gson().fromJson<NewsCatModel>(msg, NewsCatModel::class.java!!)
                cat_list=responce.data
                initTabs(cat_list)
                mProgressDialog.dismiss()
            }
        }, Constants.NEWS_CAT_API, authToken)
    }



// tab listener
    inner class TabSelectionListener:TabLayout.OnTabSelectedListener{
        override fun onTabReselected(tab: TabLayout.Tab?) {
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabSelected(tab: TabLayout.Tab?) {

            if (tab != null) {
                var cat_data:CatData=cat_list[ tab.position]
                var cat_type=cat_data.categoryName
                var page_index=cat_data.page
                if(page_index==null)page_index="1"
               // getNewsArticles(cat_type,page_index)

            }

        }

    }




    }


