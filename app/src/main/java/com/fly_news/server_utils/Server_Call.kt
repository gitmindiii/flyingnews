package com.fly_news.server_utils

import android.content.Context
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.fly_news.utilities.Constants
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import android.graphics.Bitmap
import android.widget.ImageView
import com.androidnetworking.interfaces.BitmapRequestListener
import com.androidnetworking.interfaces.StringRequestListener
import com.fly_news.R


class Server_Call {
    companion object {

        fun callPost(bodyParams: Map<String,String>,responce_server:Responce_Server,api:String,token:String){
            AndroidNetworking.post(Constants.BASE_URl + api)
                    .addHeaders(Constants.AUTH_TOKEN,token)
                    .addBodyParameter(bodyParams)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(object : JSONObjectRequestListener {
                        override fun onResponse(response: JSONObject) {
                            try {
                                val status = response.getString("status")
                                var message=""
                                if(response.has("message"))
                                    message= response.getString("message")

                                if (status == "success" || status == "ok") {
                                    responce_server.success(response.toString())
                                } else
                                    responce_server.fail(message)
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }

                        }

                        override fun onError(anError: ANError) {
                            responce_server.fail(""+anError.localizedMessage)
                            Log.e("onError: ", anError.localizedMessage)
                        }
                    })
        }

        fun callMultiPart(bodyParams: Map<String,String>, profilePic: File, responce_server:Responce_Server, api:String,token:String){
            Log.i("finalurl131232",""+Constants.BASE_URl + api)
            AndroidNetworking.upload(Constants.BASE_URl + api)
                    .addHeaders(Constants.AUTH_TOKEN,token)
                    .addMultipartParameter(bodyParams)
                    .addMultipartFile("photo",profilePic)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(object : JSONObjectRequestListener {
                        override fun onResponse(response: JSONObject) {
                            try {
                                val status = response.getString("status")
                                val message = response.getString("message")
                                if (status == "success") {
                                    responce_server.success(response.toString())
                                } else
                                    responce_server.fail(message)
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }

                        }

                        override fun onError(anError: ANError) {
                            responce_server.fail(""+anError.localizedMessage)
                            Log.e("onError: ", anError.errorBody.toString())
                        }
                    })
        }

        fun callGet(responce_server:Responce_Server,api:String,token:String){
            AndroidNetworking.get(Constants.BASE_URl + api)
                    .addHeaders(Constants.AUTH_TOKEN,token)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(object : JSONObjectRequestListener {
                        override fun onResponse(response: JSONObject) {
                            try {
                                val status = response.getString("status")
                                val message = response.getString("message")
                                if (status == "success") {
                                    responce_server.success(response.toString())
                                } else
                                    responce_server.fail(message)
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }

                        }

                        override fun onError(anError: ANError) {
                            responce_server.fail(""+anError.localizedMessage)
                            Log.e("onError: ", anError.localizedMessage)
                        }
                    })
        }

        fun callPostString(bodyParams: Map<String,String>,responce_server:Responce_Server,api:String,token:String){
            Log.i("inputServer8789",""+bodyParams.toProperties().toString())
            AndroidNetworking.post(Constants.BASE_URl + api)
                    .addHeaders(Constants.AUTH_TOKEN,token)
                    .addBodyParameter(bodyParams)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsString(object : StringRequestListener {
                        override fun onResponse(response: String?) {
                            Log.i("356467",""+response)
                        }

                        override fun onError(anError: ANError?) {
                            Log.i("356467890",""+anError.toString())
                        }




                    })
        }


        fun callImageLoad(imageUrl:String,imageView:ImageView,context:Context){
            AndroidNetworking.get(imageUrl)
                    .setTag("imageRequestTag")
                    .setPriority(Priority.MEDIUM)
                    .setBitmapMaxHeight(200)
                    .setBitmapMaxWidth(500)
                    .setBitmapConfig(Bitmap.Config.ARGB_8888)
                    .build()
                    .getAsBitmap(object : BitmapRequestListener {
                        override fun onResponse(bitmap: Bitmap) {
                            imageView.setImageBitmap(bitmap)
                        }

                        override fun onError(error: ANError) {
                            imageView.setImageResource(R.drawable.splash_screens)
                        }
                    })
        }

    }




}