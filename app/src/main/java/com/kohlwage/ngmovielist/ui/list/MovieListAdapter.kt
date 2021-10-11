package com.kohlwage.ngmovielist.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.kohlwage.ngmovielist.databinding.MovieListItemBinding
import com.kohlwage.ngmovielist.models.Movie
import com.kohlwage.ngmovielist.pictures.PictureLoader
import com.kohlwage.ngmovielist.ui.list.viewholder.MovieListViewHolder
import com.kohlwage.ngmovielist.util.diffCallback
import javax.inject.Inject

class MovieListAdapter @Inject constructor(private val pictureLoader: PictureLoader) :
    PagingDataAdapter<Movie, MovieListViewHolder>(diffCallback({ this.id }, { this })) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val binding =
            MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(binding, pictureLoader)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.onBind(item)
    }

}