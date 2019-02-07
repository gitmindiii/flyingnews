package com.fly_news.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.fly_news.R
import com.fly_news.adapter.Topic_Fragment_Adapter
import com.google.android.material.tabs.TabLayout

class Topics_Frafment : Fragment() {
    lateinit var viewpager: ViewPager;
    lateinit var sliding_tabs: TabLayout
    var topicName=arrayOf("Topics", "Saved", "History")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.topics_fragment, container, false);
        viewpager = view.findViewById(R.id.viewpager)
        sliding_tabs = view.findViewById(R.id.sliding_tabs)
        initTabs()
        return view;
    }


    fun initTabs() {
        val adapter = Topic_Fragment_Adapter(childFragmentManager, this!!.activity!!, topicName)
        viewpager.adapter = adapter
        sliding_tabs.setupWithViewPager(viewpager)


    }

}