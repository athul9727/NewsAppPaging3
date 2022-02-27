package com.example.newsapp_paging3.view


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp_paging3.R
import com.example.newsapp_paging3.databinding.ListItemBinding
import com.example.newsapp_paging3.repository.model.Article



class RecPageAdapter(
    val context: Context?,
    val clickListener: ImageClickListener
) : PagingDataAdapter<Article, RecPageAdapter.ImageViewHolder>(DataComparator){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val mDataBinding: ListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item, parent, false
        )
        return ImageViewHolder(mDataBinding)
    }



    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(position)
    }



    inner class ImageViewHolder(private val mDataBinding: ListItemBinding) :
        RecyclerView.ViewHolder(mDataBinding.root) {

        fun onBind(position: Int) {
            val row = getItem(position)
            mDataBinding.imagedata = row
            mDataBinding.imageClickInterface = clickListener
        }
    }

    object DataComparator : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
}
