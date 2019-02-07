package com.fly_news.adapter

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.fly_news.fragments.topics_fragment.Topic_List_Fragment

class Topic_Fragment_Adapter(fm: FragmentManager, activity: FragmentActivity,topicName:Array<String>) : FragmentStatePagerAdapter(fm) {

     var topicName: Array<String>
     var ref_activity: FragmentActivity

    // initializer block
    init {
        this.topicName =topicName
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
            var leadStories_Fragment= Topic_List_Fragment()
            return leadStories_Fragment
        } else {
            var leadStories_Fragment = Topic_List_Fragment()
            return leadStories_Fragment
        }
    }

    override fun getCount(): Int {
        return topicName.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return topicName.get(position)
    }
}