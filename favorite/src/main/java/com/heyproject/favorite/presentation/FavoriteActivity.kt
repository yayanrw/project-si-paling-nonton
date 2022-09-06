package com.heyproject.favorite.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.heyproject.favorite.databinding.ActivityFavoriteBinding
import com.heyproject.favorite.di.favoriteModule
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActionBar()

        loadKoinModules(favoriteModule)

        val navHostFragment =
            supportFragmentManager.findFragmentById(com.heyproject.favorite.R.id.fragmentContainerViewFavorite) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || navController.navigateUp()
    }

    private fun setActionBar() {
        supportActionBar?.apply {
            elevation = 0.0F
        }
    }
}