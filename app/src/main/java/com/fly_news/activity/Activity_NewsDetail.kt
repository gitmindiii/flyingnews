package com.fly_news.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.fly_news.R
import com.fly_news.models.ArticlesData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_newsdetail.*

class Activity_NewsDetail : AppCompatActivity(), View.OnClickListener {
    lateinit var articlesData: ArticlesData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_newsdetail)

        if (intent != null) {
            articlesData = intent.getSerializableExtra("articleObj") as ArticlesData
            if (articlesData != null) setDetails()
        }

        iv_back.setOnClickListener(this)
    }

    fun setDetails() {
        if (articlesData.title != null) tv_news_heading.text = articlesData.title
        if (articlesData.content != null) {
                var article_content=articlesData.content
                var index=(article_content.lastIndexOf("[")-2)
                 if(index > 0)  txt_content.text =(article_content.substring(0,index))+"."
                 else txt_content.text=article_content

        }
        if (articlesData.author != null) txt_author.text = articlesData.author
        if (articlesData.publishedAt != null) {
            var datetime_str = articlesData.publishedAt
            var date_str = datetime_str.split("T")[0]
            txt_date.text = date_str
        }
        if (articlesData.source.name != null) txt_source.text = articlesData.source.name

        if (articlesData.urlToImage != null) {
            Picasso.get()
                    .load(articlesData.urlToImage)
                    .placeholder(R.drawable.placeholder_img)
                    .error(R.drawable.placeholder_img)
                    .into(img_article)

        }

    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.iv_back -> onBackPressed()
            }
        }
    }
}