package com.example.newsapp_paging3.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp_paging3.repository.model.Article
import java.lang.annotation.Native
import java.util.jar.Attributes


@Database(
    entities = [Article::class],
    version = 1, exportSchema = false
)

@TypeConverters(
    Converters::class,
    ImageItemConverter::class,)
abstract class ImagesDatabase : RoomDatabase() {
    abstract val imgDao: ImagesDao
}