package com.bentley.codingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bentley.codingtest.databinding.ActivityMainBinding
import com.bentley.codingtest.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}