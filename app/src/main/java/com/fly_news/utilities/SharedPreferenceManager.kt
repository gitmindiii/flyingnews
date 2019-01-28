package com.fly_news.utilities

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceManager {
    var context: Context

    constructor(context: Context) {
         this.context = context;

    }
    companion object {
        val USER_NAME="user_name";
        val USER_EMAIL="user_email";
        val AUTH_TKOEN="auth_Token";
        val REMEMBER_AUTH="remember_auth";
        val PREFS_FILENAME = "com.fly_news.prefs"
    }


    fun getPreference():SharedPreferences{
        return  context.getSharedPreferences(PREFS_FILENAME, 0)
    }


    fun addString(key:String,value:String){
        val prefs=getPreference()
        val editor = prefs!!.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key:String):String{
        val prefs=getPreference()
        return prefs.getString(key,"")
    }

    fun addBool(key:String,value:Boolean){
        val prefs=getPreference()
        val editor = prefs!!.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBool(key:String):Boolean{
        val prefs=getPreference()
        return prefs.getBoolean(key,false)
    }
}