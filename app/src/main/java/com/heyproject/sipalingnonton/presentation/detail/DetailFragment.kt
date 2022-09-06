package com.heyproject.sipalingnonton.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.heyproject.core.core.IMAGE_URL_ORIGIN
import com.heyproject.core.domain.model.Movie
import com.heyproject.sipalingnonton.R
import com.heyproject.sipalingnonton.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {
    private val viewModel: DetailViewModel by viewModel()
    private val args: DetailFragmentArgs by navArgs()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var movie: Movie
    private var isFavorite: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movie = Movie(
            id = args.movieId,
            title = args.title,
            isFavorite = args.isFavorite,
            posterPath = args.posterPath,
            overview = args.overview,
            backdropPath = args.backdropPath,
            releaseDate = args.releaseDate,
            voteAverage = args.voteAverage.toDouble()
        )

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            detailFragment = this@DetailFragment
            movieDetail = movie
            imgUrl = """$IMAGE_URL_ORIGIN${args.posterPath}"""
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
                    requireContext(),
                    R.drawable.ic_baseline_favorite_24
                )
            )
        } else {
            binding.ibFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_favorite_border_24
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}