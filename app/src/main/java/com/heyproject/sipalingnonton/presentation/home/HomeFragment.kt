package com.heyproject.sipalingnonton.presentation.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.heyproject.core.core.Resource
import com.heyproject.core.data.ui.MovieAdapter
import com.heyproject.sipalingnonton.R
import com.heyproject.sipalingnonton.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

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
            val toDetailFragment = HomeFragmentDirections.actionHomeFragmentToDetailActivity(
                movie = selectedData
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
                        movieAdapter.submitList(movies.data)
                        if (movies.data.isNullOrEmpty()) {
                            binding.rvMovies.visibility = View.GONE
                            binding.tvNodata.visibility = View.VISIBLE
                        } else {
                            binding.tvNodata.visibility = View.GONE
                            binding.rvMovies.visibility = View.VISIBLE
                        }
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
        _binding?.unbind()
        _binding?.lifecycleOwner = null
        _binding?.viewModel = null
        _binding?.homeFragment = null


        _binding?.errorScreen?.homeFragment = null
        _binding?.rvMovies?.adapter = null
        activity?.run {
            supportFragmentManager.beginTransaction().remove(this@HomeFragment)
                .commitAllowingStateLoss()
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.options_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.action_favorite -> {
                val uri = Uri.parse("sipalingnonton://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
                true
            }
            R.id.action_setting -> {
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
                true
            }
            else -> {
                false
            }
        }
    }
}