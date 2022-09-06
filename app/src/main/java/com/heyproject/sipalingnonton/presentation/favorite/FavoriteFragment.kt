package com.heyproject.sipalingnonton.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.heyproject.sipalingnonton.data.ui.MovieAdapter
import com.heyproject.sipalingnonton.databinding.FragmentFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectedData ->
            val toDetailFragment =
                FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(
                    movieId = selectedData.id,
                    title = selectedData.title,
                    isFavorite = selectedData.isFavorite,
                    posterPath = selectedData.posterPath,
                    overview = selectedData.overview,
                    releaseDate = selectedData.releaseDate,
                    backdropPath = selectedData.backdropPath,
                    voteAverage = selectedData.voteAverage.toString()
                )
            findNavController().navigate(toDetailFragment)
        }

        viewModel.favoriteMovies.observe(viewLifecycleOwner) { favoriteMovies ->
            movieAdapter.setData(favoriteMovies)
        }

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            favoriteFragment = this@FavoriteFragment
            rvMovies.apply {
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}