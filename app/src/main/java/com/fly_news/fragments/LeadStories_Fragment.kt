package com.fly_news.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fly_news.R
import com.fly_news.adapter.NewsListAdapter
import com.fly_news.models.ArticlesData
import com.fly_news.models.NewsArticlesModel
import com.fly_news.server_utils.Responce_Server
import com.fly_news.server_utils.Server_Call
import com.fly_news.utilities.Constants
import com.fly_news.utilities.ProgressDialog_Utils
import com.fly_news.utilities.SharedPreferenceManager
import com.google.gson.Gson
import org.jetbrains.anko.toast


class LeadStories_Fragment() : Fragment() {
    lateinit var mProgressDialog: Dialog;
    lateinit var recycler_view:RecyclerView
    lateinit var news_adapter:NewsListAdapter
    lateinit var newslist:ArrayList<ArticlesData>
    lateinit var layoutManager:RecyclerView.LayoutManager
    var page_index="1"
    var catName:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_leadstories, container, false);

        catName= arguments!!.getString("categoryName")
        recycler_view=view.findViewById(R.id.recycler_view)
        mProgressDialog = ProgressDialog_Utils(this!!.context!!)
        newslist=ArrayList<ArticlesData>()
        initNewsListView()
        getNewsArticles(catName,page_index);
        return view;
    }


    fun initNewsListView(){
        layoutManager=LinearLayoutManager(context)
        recycler_view.layoutManager=layoutManager
        news_adapter=NewsListAdapter(newslist, this!!.activity!!);
        recycler_view.adapter=news_adapter
        recycler_view.addOnScrollListener(PaginationScrollListener())
    }


    // getting news // pageSize is number of news and page is index of page
    fun getNewsArticles(catType:String,page_index:String) {
        val inputMap = mapOf("pageSize" to Constants.PAGE_SIZE,
                "page" to page_index, "to" to "", "from" to "",
                "filter" to "", "type" to "everything", "category" to catType,"is_filter" to "0")

        mProgressDialog.show()
        val authToken = SharedPreferenceManager(this!!.activity!!).getString(SharedPreferenceManager.AUTH_TKOEN)
        Server_Call.callPost(inputMap,object : Responce_Server {
            override fun fail(msg: String) {
                activity!!.toast(msg)
                mProgressDialog.dismiss()
            }

            override fun success(msg: String) {
                val responce: NewsArticlesModel = Gson().fromJson<NewsArticlesModel>(msg, NewsArticlesModel::class.java!!)
                var listOfNews= responce.articles as ArrayList<ArticlesData>
                newslist.addAll(listOfNews);
                news_adapter.notifyDataSetChanged()
                mProgressDialog.dismiss()
                isLoading=false

            }
        }, Constants.NEWS_ARTICLES_API, authToken)
    }

    var isLoading =false;
    inner class PaginationScrollListener : RecyclerView.OnScrollListener(){

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val visibleItemCount = layoutManager.getChildCount()
            val totalItemCount = layoutManager.getItemCount()
            val firstVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                if ((visibleItemCount + firstVisibleItemPosition) >=
                        totalItemCount && firstVisibleItemPosition >= 0) {

                    if(!isLoading) {
                        isLoading=true
                        var check_index = page_index.toInt()
                        check_index++
                        page_index=check_index.toString()
                        Log.i("index234", "" + page_index)
                        getNewsArticles(catName, page_index)
                    }
            }
        }







    }




}