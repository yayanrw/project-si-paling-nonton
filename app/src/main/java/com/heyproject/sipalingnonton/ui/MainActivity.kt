package com.heyproject.sipalingnonton.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.heyproject.sipalingnonton.databinding.ActivityMainBinding
import com.heyproject.sipalingnonton.presentation.home.MovieHomeViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MovieHomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActionBar()
    }

    private fun setActionBar() {
        supportActionBar?.apply {
            elevation = 0.0F
        }
    }
}