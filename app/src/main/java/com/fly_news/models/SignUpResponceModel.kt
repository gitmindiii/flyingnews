package com.fly_news.models



data class SignUpResponceModel (
        val status: String = "",
        val message: String = "",
        val userDetail: UserDetailBean)


    data class UserDetailBean(val userId: String,
                         val fullName: String,
                         val email: String,
                         val password: String,
                         val profileImage: String,
                         val socialType: String,
                         val socialId: String,
                         val deviceType: String,
                         val deviceToken: String,
                         val authToken: String,
                         val forgetPass: String,
                         val is_notify: String,
                         val status: String,
                         val crd: String,
                         val upd: String)
