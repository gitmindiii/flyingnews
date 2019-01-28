package com.fly_news.adapter

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.fly_news.fragments.LeadStories_Fragment
import com.fly_news.models.CatData

class Home_Fragment_Adapter(fm: FragmentManager, activity: FragmentActivity, list: List<CatData>) : FragmentPagerAdapter(fm) {


    lateinit var tab_list: List<CatData>
    lateinit var ref_activity: FragmentActivity

    // initializer block
    init {
        tab_list =list
        ref_activity=activity
    }

    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            val imm = ref_activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            //Find the currently focused view, so we can grab the correct window token from it.
            var view = ref_activity.getCurrentFocus()
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = View(ref_activity)
            }
            imm.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
            return LeadStories_Fragment(tab_list[position].categoryName)
        } else
            return LeadStories_Fragment(tab_list[position].categoryName)
    }

    override fun getCount(): Int {
       return tab_list.size;
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tab_list[position].categoryName
    }

}