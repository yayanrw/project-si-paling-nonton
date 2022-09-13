package com.heyproject.sipalingnonton.presentation.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.navArgs
import com.google.android.material.snackbar.Snackbar
import com.heyproject.core.core.IMAGE_URL_ORIGIN
import com.heyproject.core.domain.model.Movie
import com.heyproject.sipalingnonton.R
import com.heyproject.sipalingnonton.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val args: DetailActivityArgs by navArgs()
    private val viewModel: DetailViewModel by viewModel()
    private lateinit var movie: Movie
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movie = args.movie
        supportActionBar?.title = movie.title
        binding.apply {
            lifecycleOwner = this@DetailActivity
            detailActivity = this@DetailActivity
            movieDetail = movie
            imgUrl = """$IMAGE_URL_ORIGIN${args.movie.posterPath}"""
            executePendingBindings()
        }
        isFavorite = movie.isFavorite
        setFavIcon(movie.isFavorite)
    }

    fun setFavoriteMovie() {
        isFavorite = !isFavorite
        viewModel.setFavoriteTourism(movie, isFavorite)
        setFavIcon(isFavorite)
        if (isFavorite) {
            Snackbar.make(binding.root, getString(R.string.success_add), Snackbar.LENGTH_SHORT)
                .show()
        } else {
            Snackbar.make(binding.root, getString(R.string.success_remove), Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    private fun setFavIcon(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.ibFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_24
                )
            )
        } else {
            binding.ibFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_border_24
                )
            )
        }
    }
}