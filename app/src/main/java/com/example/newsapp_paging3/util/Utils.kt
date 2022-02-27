package com.example.newsapp_paging3.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import retrofit2.Response

object Utils {
    fun <T : Any> handleApiError(resp: Response<T>): AppResult.Error {
        val error = ApiErrorUtils.parseError(resp)
        return AppResult.Error(Exception(error.message))
    }

    fun <T : Any> handleSuccess(response: Response<T>): AppResult<T> {
        response.body()?.let {
            return AppResult.Success(it)
        } ?: return handleApiError(response)
    }


}

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    Picasso.with(imageView.context)
        .load(url)
        .fit()
        .into(imageView)
}