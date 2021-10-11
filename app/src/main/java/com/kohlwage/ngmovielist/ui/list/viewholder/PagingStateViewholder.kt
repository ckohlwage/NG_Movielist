package com.kohlwage.ngmovielist.ui.list.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.kohlwage.ngmovielist.R
import com.kohlwage.ngmovielist.databinding.MoviesListLoadStateFooterBinding

class MovieLoadStateViewHolder(
    private val binding: MoviesListLoadStateFooterBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryLoadText.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.movieErrorList.text = loadState.error.localizedMessage
        }
        binding.progressLoadingMore.isVisible = loadState is LoadState.Loading
        binding.retryLoadText.isVisible = loadState is LoadState.Error
        binding.movieErrorList.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): MovieLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.movies_list_load_state_footer, parent, false)
            val binding = MoviesListLoadStateFooterBinding.bind(view)
            return MovieLoadStateViewHolder(binding, retry)
        }
    }
}