package com.example.newsapp_paging3.view


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.newsapp_paging3.R
import com.example.newsapp_paging3.databinding.FragmentImagedataDetailsBinding
import com.example.newsapp_paging3.repository.model.Article
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetailsFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(data: Article) =
            ImageDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("data_row", data)
            }
        }
    }

    private var imagedata: Article? = null
    private lateinit var mDataBinding: FragmentImagedataDetailsBinding


    override fun onAttach(context: Context) {
        super.onAttach(context)
        imagedata =  arguments?.getParcelable("data_row")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mDataBinding  = DataBindingUtil.inflate(inflater,
            R.layout.fragment_imagedata_details, container, false)
        val mRootView = mDataBinding.root
        mDataBinding.lifecycleOwner = this
        return mRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        enableBackButton()
        mDataBinding.imagedata = imagedata
    }

    private fun enableBackButton() {
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as? AppCompatActivity)?.supportActionBar?.setHomeButtonEnabled(true)
    }
    
}