package com.kohlwage.ngmovielist.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.kohlwage.ngmovielist.R
import com.kohlwage.ngmovielist.databinding.FragmentMovieListBinding
import com.kohlwage.ngmovielist.pictures.PictureLoader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MovieListFragment : Fragment(R.layout.fragment_movie_list) {
    @Inject
    lateinit var pictureLoader: PictureLoader

    @Inject
    lateinit var movieAdapter: MovieListAdapter

    private val pageViewModel: MovieListViewModel by activityViewModels()

    private var _binding: FragmentMovieListBinding? = null
    private val binding: FragmentMovieListBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        registerObservers()
    }

    private fun initViews() {
        binding.movieRecycler.adapter = movieAdapter.withLoadStateHeaderAndFooter(
            header = MovieLoadStateAdapter { movieAdapter.retry() },
            footer = MovieLoadStateAdapter { movieAdapter.retry() }
        )
        movieAdapter.addLoadStateListener { loadState -> handleLoadStates(loadState) }
        val layoutManager: GridLayoutManager =
            binding.movieRecycler.layoutManager as GridLayoutManager
        layoutManager.spanSizeLookup =
            object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    val spanCount = resources.getInteger(R.integer.movie_list_span_count)
                    return when (movieAdapter.getItemViewType(position)) {
                        MovieListAdapter.MOVIE_VIEW_TYPE -> 1
                        else -> spanCount
                    }
                }
            }


        binding.swipeRefresh.setOnRefreshListener { movieAdapter.refresh() }
        binding.error.setOnClickListener { movieAdapter.retry() }

    }

    private fun handleLoadStates(loadState: CombinedLoadStates) {
        val isListEmpty = loadState.refresh is LoadState.NotLoading && movieAdapter.itemCount == 0

        // Show empty state if list is empty
        binding.movieEmptyState.isVisible = isListEmpty
        // Show list if it is not empty
        binding.movieRecycler.isVisible = !isListEmpty

        // Only shows the list if refresh succeeds.
        binding.movieRecycler.isVisible = loadState.source.refresh is LoadState.NotLoading
        // Show loading spinner during initial load or refresh.
        binding.swipeRefresh.isRefreshing = loadState.source.refresh is LoadState.Loading
        // Show the retry state if initial load or refresh fails.
        binding.error.isVisible = loadState.source.refresh is LoadState.Error
    }

    private fun registerObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            pageViewModel.movieList.collectLatest { pagingData ->
                movieAdapter.submitData(pagingData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val MOVIE_ID = "movie_id"
    }

}