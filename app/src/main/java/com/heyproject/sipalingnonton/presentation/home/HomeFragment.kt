package com.heyproject.sipalingnonton.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.heyproject.sipalingnonton.R
import com.heyproject.sipalingnonton.core.Resource
import com.heyproject.sipalingnonton.data.ui.MovieAdapter
import com.heyproject.sipalingnonton.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

const val TAG = "HomeFragment"

class HomeFragment : Fragment(), MenuProvider {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectedData ->
            val toDetailFragment = HomeFragmentDirections.actionHomeFragmentToDetailFragment(
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

        viewModel.movie.observe(viewLifecycleOwner) { movies ->
            if (movies != null) {
                when (movies) {
                    is Resource.Loading -> binding.circularProgressIndicator.visibility =
                        View.VISIBLE
                    is Resource.Success -> {
                        binding.circularProgressIndicator.visibility = View.GONE
                        movieAdapter.setData(movies.data)
                    }
                    is Resource.Error -> {
                        binding.circularProgressIndicator.visibility = View.GONE
                        binding.errorScreen.root.visibility = View.VISIBLE
                        binding.errorScreen.tvError.text =
                            movies.message ?: getString(R.string.oops)
                    }
                }
            }
        }

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = viewModel
            homeFragment = this@HomeFragment
            errorScreen.homeFragment = this@HomeFragment
            rvMovies.apply {
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.options_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.action_search -> {
                Log.d(TAG, "Search")
                true
            }
            R.id.action_notification -> {
                Log.d(TAG, "Notif")
                true
            }
            else -> {
                false
            }
        }
    }

    fun fetchMovies() {

    }
}