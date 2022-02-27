package com.example.newsapp_paging3.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp_paging3.R
import com.example.newsapp_paging3.databinding.FragmentBookmarkBinding
import com.example.newsapp_paging3.databinding.FragmentImagesBinding
import com.example.newsapp_paging3.repository.model.Article
import com.example.newsapp_paging3.util.replaceFragment
import com.example.newsapp_paging3.viewmodel.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : Fragment() , ImageClickListener {

    //  private val ViewModel by viewModel<ViewModel>()
    private val viewModel: ViewModel by viewModels()
    private lateinit var recAdapter: RecAdapter
    //private lateinit var mainactivity: Activity
    private lateinit var mDataBinding: FragmentBookmarkBinding

    private var _binding: FragmentBookmarkBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        mDataBinding  = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_bookmark,
            container,
            false)

        val mRootView = mDataBinding.root
        mDataBinding.lifecycleOwner = this
        //mainactivity = activity?.p

        return mRootView
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setView()
        removeBackButton()
        mDataBinding.viewModel = viewModel
        viewModel.getallbookmarkdata()
//        viewModel.connectivityLiveData.observe(viewLifecycleOwner, Observer { isAvailable ->
//            when (isAvailable) {
//                true -> {
//                    if (!viewModel.inputtext.value.isNullOrEmpty()) {
//                        if (viewModel.imageList.value.isNullOrEmpty()) {
//                            viewModel.set_edittext()
//                        }
//                    }
//                }
//                false -> {
//                    //ViewModel.imageList.value = null
//                    Toast.makeText(context,"No Network", Toast.LENGTH_LONG).show()
//                }
//            }
//        })

        viewModel.bookmarkList.observe(viewLifecycleOwner, Observer {

            if (it != null) {
                if (it.isNotEmpty()) {
                    Log.i("checkstatus", it.toString())
                    recAdapter.setlist(it)
                }
            }

        })


    }

    private fun removeBackButton() {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as? AppCompatActivity)?.supportActionBar?.setHomeButtonEnabled(false)
    }

    private fun setView() {
        recAdapter = RecAdapter(context, this)
        mDataBinding.recycleview.layoutManager = LinearLayoutManager(activity)
        mDataBinding.recycleview.setHasFixedSize(true)
        mDataBinding.recycleview.setItemViewCacheSize(12)
        mDataBinding.recycleview.adapter = recAdapter


    }

    override fun onItemClick(article : Article) {
        (activity as MainActivity).replaceFragment(
            ImageDetailsFragment.newInstance(article),
            R.id.fragment_container, "imagedetails")
    }

    override fun onBookMarkClick(article: Article) {
        viewModel.removedata(article)
        recAdapter.removeitem(article)
    }






}