package com.example.newsapp_paging3.view

import com.example.newsapp_paging3.repository.model.Article


interface ImageClickListener {
    fun onItemClick(article : Article)
    fun onBookMarkClick(article : Article)
}