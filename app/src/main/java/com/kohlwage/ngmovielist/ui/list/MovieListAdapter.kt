package com.kohlwage.ngmovielist.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kohlwage.ngmovielist.databinding.MovieListItemBinding
import com.kohlwage.ngmovielist.models.Movie

class MovieListAdapter(  private val onMovieClick: (Movie) -> Unit = {}) : PagingDataAdapter<Movie, MovieListAdapter.MovieViewHolder>(movieDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.onBind(item)
    }

    inner class MovieViewHolder(private val binding: MovieListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(movie: Movie) {
            binding.movie = movie
            binding.root.setOnClickListener { onMovieClick(movie) }
        }
    }

    companion object {
        object movieDiffer : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem

        }

    }
}