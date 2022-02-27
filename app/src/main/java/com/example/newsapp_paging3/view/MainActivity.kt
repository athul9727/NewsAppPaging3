package com.example.newsapp_paging3.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.newsapp_paging3.R
import com.example.newsapp_paging3.databinding.ActivityMainBinding
import com.example.newsapp_paging3.util.replaceFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val fragment1: Fragment = ImagesFragment()
    val fragment2: Fragment = BookmarkFragment()
    var active: Fragment = fragment1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bottomNav.menu.getItem(0).isCheckable = true
       // addFragment(ImagesFragment())
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment2, "2").hide(fragment2).commit();
        supportFragmentManager.beginTransaction().add(R.id.fragment_container,fragment1, "1").commit();
        binding.bottomNav.setOnNavigationItemSelectedListener {menu ->

            when(menu.itemId){

                R.id.news -> {
                   // addFragment(ImagesFragment())
                    supportFragmentManager.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                    true
                }

                R.id.bookmark -> {
                   // addFragment(BookmarkFragment())
                    supportFragmentManager.beginTransaction().hide(active).show(fragment2).commit();
                    active = fragment2;
                    true
                }
                else -> false
            }
        }

    }

    private fun addFragment(fr : Fragment) {
        replaceFragment(
            fr, R.id.fragment_container
        )
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}