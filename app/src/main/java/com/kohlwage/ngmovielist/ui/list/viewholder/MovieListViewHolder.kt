package com.kohlwage.ngmovielist.ui.list.viewholder

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.kohlwage.ngmovielist.R
import com.kohlwage.ngmovielist.databinding.MovieListItemBinding
import com.kohlwage.ngmovielist.models.Movie
import com.kohlwage.ngmovielist.pictures.PictureLoader
import com.kohlwage.ngmovielist.ui.list.MovieListFragment

class MovieListViewHolder(
    private val binding: MovieListItemBinding,
    private val pictureLoader: PictureLoader
) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(movie: Movie) {
        binding.movie = movie
        pictureLoader.loadSmallPictureFromToken(binding.moviePoster, movie.posterToken)
        binding.root.setOnClickListener {
            val bundle = Bundle(2).apply {
                this.putString(MovieListFragment.MOVIE_ID, movie.id)
            }
            Navigation.findNavController(binding.root).navigate(R.id.movieDetailFragment, bundle)
        }
    }
}
