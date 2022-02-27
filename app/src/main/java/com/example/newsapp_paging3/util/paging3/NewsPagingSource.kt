package com.example.newsapp_paging3.util.paging3

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp_paging3.repository.Api
import com.example.newsapp_paging3.repository.Repository
import com.example.newsapp_paging3.repository.model.Article
import com.example.newsapp_paging3.util.AppResult

class NewsPagingSource(
    private val str:String,
    private val repository: Repository,
)
    : PagingSource<Int, Article>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        try {
            //for first case it will be null, then we can pass some default value, in our case it's 1
            val page = params.key ?: 1
            val response = repository.getAlldata(str, page.toString())
            var imageList = mutableListOf<Article>()

            when (response) {
                is AppResult.Success -> {
                    imageList = response.successData.articles.toMutableList()

                }
                else -> {}
            }


            return LoadResult.Page(
                data = imageList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (imageList.isEmpty()) null else page + 1
            )

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        TODO("Not yet implemented")
    }
}