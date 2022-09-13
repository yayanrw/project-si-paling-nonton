package com.heyproject.favorite.presentation.favorite

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.heyproject.core.data.ui.MovieAdapter
import com.heyproject.favorite.databinding.FragmentFavoriteBinding
import com.heyproject.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(favoriteModule)
    }

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
            val uri = Uri.parse("sipalingnonton://detail_movie")
            val intent = (Intent(Intent.ACTION_VIEW, uri).putExtra("movie", selectedData))
            startActivity(intent)
        }

        viewModel.favoriteMovies.observe(viewLifecycleOwner) { favoriteMovies ->
            movieAdapter.submitList(favoriteMovies)
            if (favoriteMovies.isEmpty()) {
                binding.rvMovies.visibility = View.GONE
                binding.tvNodata.visibility = View.VISIBLE
            } else {
                binding.tvNodata.visibility = View.GONE
                binding.rvMovies.visibility = View.VISIBLE
            }
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