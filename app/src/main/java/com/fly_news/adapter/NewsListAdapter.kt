package com.fly_news.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.fly_news.R
import com.fly_news.activity.Activity_NewsDetail
import com.fly_news.models.ArticlesData
import com.fly_news.utilities.Common_Views
import kotlinx.android.synthetic.main.news_article_view.view.*
import android.os.Bundle




class NewsListAdapter(val items: ArrayList<ArticlesData>, val context: Context) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListAdapter.ViewHolder {
        var view = NewsListAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.news_article_view, parent, false))
        return view
    }

    override fun getItemCount(): Int {
        Log.i("23409239", "" + items.size)
        return items.size
    }

    override fun onBindViewHolder(holder: NewsListAdapter.ViewHolder, position: Int) {

        var articlesData = items[position];
        if (articlesData.title != null) holder.tv_heading.text = articlesData.title
        if (articlesData.content != null) holder.tv_content.text = articlesData.content
        if (articlesData.source.name != null) holder.tv_news_channel.text = articlesData.source.name
        if (articlesData.urlToImage != null) {


         /*   var requestOptions = RequestOptions()
            requestOptions = requestOptions.centerCrop().optionalTransform(RoundedCorners(16))*/

            Glide.with(context)
                    .load(articlesData.urlToImage)
                   // .apply(requestOptions)
                    .into(holder.img_article)




        }

        holder.parent_view.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                goToDetails(items[position])
            }
        });


    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var parent_view=view.parent_view;
        val tv_heading = view.tv_heading
        val tv_content = view.tv_content
        val img_article = view.img_article
        val tv_news_channel = view.tv_news_channel

    }


    fun goToDetails(articlesData:ArticlesData){
        Activity_NewsDetail.articlesData=articlesData;
        var intent = Intent(context, Activity_NewsDetail::class.java)
       /* val b = Bundle()
        b.putParcelable("articleObj", articlesData)
        intent.putExtra("articleObj",articlesData)*/
       // intent.putExtra("bundle",b)
        context.startActivity(intent)
    }

}