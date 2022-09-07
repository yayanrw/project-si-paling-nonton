package com.heyproject.core.data.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heyproject.core.R
import com.heyproject.core.core.IMAGE_URL_SMALL
import com.heyproject.core.databinding.ItemMovieBinding
import com.heyproject.core.domain.model.Movie

class MovieAdapter :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private val movies = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newMovies: List<Movie>?) {
        if (newMovies.isNullOrEmpty()) return
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private var binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie?) {
            binding.apply {
                imgUrl = """$IMAGE_URL_SMALL${movie?.posterPath}"""
                tvItemTitle.text = movie?.title
                tvItemSubtitle.text =
                    binding.root.context.getString(R.string.release_date, movie?.releaseDate)
                executePendingBindings()
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(movies[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movies.size
}