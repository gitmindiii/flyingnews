package com.fly_news.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fly_news.R
import com.fly_news.activity.Activity_NewsDetail
import com.fly_news.models.ArticlesData
import com.fly_news.server_utils.Responce_Server
import com.fly_news.server_utils.Server_Call
import com.fly_news.utilities.Constants
import com.fly_news.utilities.SharedPreferenceManager
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_article_view.view.*


class NewsListAdapter(val items: ArrayList<ArticlesData>, val context: Context) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListAdapter.ViewHolder {
        var view = NewsListAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.news_article_view, parent, false))
        return view
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsListAdapter.ViewHolder, position: Int) {

        var articlesData = items[position];
        if (articlesData.title != null) holder.tv_heading.text = articlesData.title
        if (articlesData.content != null) holder.tv_content.text = articlesData.content
        if (articlesData.source.name != null) holder.tv_news_channel.text = articlesData.source.name
        if (articlesData.urlToImage != null) {

            Picasso.get()
                    .load(articlesData.urlToImage)
                    .placeholder(R.drawable.placeholder_img)
                    .error(R.drawable.placeholder_img)
                    .into(holder.img_article)
        }


        holder.iv_bookmark.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                rememberNews(items[position])
            }

        })

        holder.parent_view.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                goToDetails(items[position])
            }
        });


    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var parent_view = view.parent_view;
        val tv_heading = view.tv_heading
        val tv_content = view.tv_content
        val img_article = view.img_article
        val tv_news_channel = view.tv_news_channel
        var iv_bookmark = view.iv_bookmark;

    }


    fun goToDetails(articlesData: ArticlesData) {
        var intent = Intent(context, Activity_NewsDetail::class.java)
        intent.putExtra("articleObj", articlesData)
        context.startActivity(intent)
    }

    fun rememberNews(articlesData: ArticlesData){
        var gson= Gson()
        var news_json=gson.toJson(articlesData)
        var inputMap= mapOf("news" to news_json, "newsTitle" to articlesData.title)
        val authToken = SharedPreferenceManager(context).getString(SharedPreferenceManager.AUTH_TKOEN)
        Server_Call.callPostWithoutListen(inputMap,Constants.SAVE_NEWS_API,authToken)

    }


}