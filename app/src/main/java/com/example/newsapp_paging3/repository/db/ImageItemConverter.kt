package com.example.newsapp_paging3.repository.db


import androidx.room.TypeConverter
import com.example.newsapp_paging3.repository.model.ImageListResponseItem
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson


class ImageItemConverter {
    @TypeConverter
    fun stringToName(json: String): ImageListResponseItem? {
        val gson = Gson()
        val type = object : TypeToken<ImageListResponseItem>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun nameToString(name: ImageListResponseItem): String {
        val gson = Gson()
        val type = object : TypeToken<ImageListResponseItem>() {}.type
        return gson.toJson(name, type)
    }
}
