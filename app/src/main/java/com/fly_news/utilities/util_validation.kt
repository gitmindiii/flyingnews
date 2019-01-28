package com.fly_news.utilities

import android.content.Context
import android.util.Patterns
import android.widget.TextView
import com.fly_news.R

class util_validation {

    private val USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$"
    private val FULLNAME_PATTERN = "^[\\p{L} .'-]+$"

    companion object {
        fun isEmailValid(context:Context, email: String): String {
            if(email.isEmpty()) return context.getString(R.string.please_enter_valid_email_id)
            else if(email.contains(" ")) return context.getString(R.string.email_can_hold_space)
            else if(!Patterns.EMAIL_ADDRESS.toRegex().matches(email)) return context.getString(R.string.please_enter_valid_email_id)
            else return "done"
        }

        fun isPassValid(context:Context,pass: String): String {
            if(pass.isEmpty()) return context.getString(R.string.please_enter_password)
            else if(pass.length<6) return context.getString(R.string.pass_should_have_minimum_six_char)
            else return "done"
        }

        fun isNameValidStr(context:Context,fullName: String): String {
            if(fullName.isEmpty()) return context.getString(R.string.please_enter_fullname)
            else if(fullName.length<2) return context.getString(R.string.full_name_should_not_less_than_characters)
            else return "done"
        }
    }

}