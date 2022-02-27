package com.example.newsapp_paging3.repository.db
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//When you want to store some custom types objects like the e.g lists, models, list of models etc,
// in the database, you can use Type Converters.
// It converts your custom object type into a known type in terms of database types.
// This is most useful features of Room persistent library.

class Converters {

    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayListToString(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }


}