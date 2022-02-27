package com.example.newsapp_paging3.repository.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsModel(
    val status: String,
    val totalResults: Long,
    val articles: List<Article>
): Parcelable
@Entity(tableName = "FavArticlesTable")
@Parcelize
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
): Parcelable
