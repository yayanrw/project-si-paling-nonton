package com.heyproject.sipalingnonton.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.heyproject.sipalingnonton.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
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