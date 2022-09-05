package com.heyproject.sipalingnonton.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.heyproject.sipalingnonton.core.IMAGE_URL_ORIGIN
import com.heyproject.sipalingnonton.databinding.FragmentDetailBinding
import com.heyproject.sipalingnonton.domain.model.Movie
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {
    private val viewModel: DetailViewModel by viewModel()
    private val args: DetailFragmentArgs by navArgs()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            detailFragment = this@DetailFragment
            content.movie = Movie(
                id = args.movieId,
                title = args.title,
                isFavorite = args.isFavorite,
                posterPath = args.posterPath,
                overview = args.overview,
                backdropPath = args.backdropPath,
                releaseDate = args.releaseDate,
                voteAverage = 0.0
            )
            imgUrl = """$IMAGE_URL_ORIGIN${args.posterPath}"""
            executePendingBindings()
        }
    }
}