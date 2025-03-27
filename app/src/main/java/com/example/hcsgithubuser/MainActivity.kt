package com.example.hcsgithubuser

import android.os.Bundle
import android.view.LayoutInflater
import com.example.hcsgithubuser.base.presentation.BaseActivity
import com.example.hcsgithubuser.databinding.ActivityMainBinding
import com.example.hcsgithubuser.home.presentation.HomeFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun setupViews(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment.newInstance(null)) // Add the fragment
                .commit()
        }
    }


    override fun inflateLayout(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}