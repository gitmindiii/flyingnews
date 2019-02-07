package com.fly_news.utilities

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import java.io.FileNotFoundException
import java.io.IOException

class Constants {

    companion object {
        val AUTH_TOKEN="authToken"
        val SERVER_URl="http://hwsrv-403298.hostwindsdns.com/development/"
        val API_VERSION="api_v1/"
        val BASE_URl = SERVER_URl+API_VERSION;

        // api methods
        val LOGIN_API="userLogin"
        val SIGNUP_API="userRegistration"
        val NEWS_CAT_API="news/getCategory"
        val NEWS_ARTICLES_API="news/getNews"
        val SAVE_NEWS_API="news/addNewsViewed"

        val PAGE_SIZE="10";

    }
}