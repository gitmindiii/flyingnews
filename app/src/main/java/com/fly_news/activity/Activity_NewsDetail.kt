package com.fly_news.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.fly_news.R
import com.fly_news.models.ArticlesData
import kotlinx.android.synthetic.main.activity_newsdetail.*

class Activity_NewsDetail : AppCompatActivity(), View.OnClickListener{

companion object {
    lateinit var articlesData: ArticlesData
}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_newsdetail)
        setDetails()
      /*  if (intent != null) {

                articlesData=intent.getParcelableExtra("articleObj")
                setDetails()


            *//*val b = intent.getBundleExtra("bundle")
            if(b!=null) {

                if(b.getParcelable("articleObj"?:ArticlesData)!=null) {
                    articlesData=b.getParcelable("articleObj") as ArticlesData
                    setDetails()
                }
            }*//*

        }*/

        iv_back.setOnClickListener(this)
    }

    fun setDetails() {
        if (articlesData.title != null) tv_news_heading.text = articlesData.title
        if (articlesData.content != null) txt_content.text = articlesData.content
        if (articlesData.author != null) txt_author.text = articlesData.author
        if (articlesData.publishedAt != null) {
            var datetime_str=articlesData.publishedAt
            var date_str=datetime_str.split("T")[0]
            txt_date.text = date_str
        }
        if (articlesData.source.name != null) txt_source.text = articlesData.source.name

        if (articlesData.urlToImage != null) {
            Glide.with(this)
                    .load(articlesData.urlToImage)
                    .into(img_article)

        }

    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.iv_back-> onBackPressed()
            }
        }
    }
}