package com.kohlwage.ngmovielist.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.kohlwage.ngmovielist.databinding.FragmentMovieDetailsBinding
import com.kohlwage.ngmovielist.pictures.PictureLoader
import com.kohlwage.ngmovielist.ui.list.MovieListFragment.Companion.MOVIE_ID
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    @Inject
    lateinit var pictureLoader: PictureLoader

    private val detailViewModel: MovieDetailsViewModel by activityViewModels()

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding: FragmentMovieDetailsBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        loadMovieDetails()
        initObservers()
    }

    private fun loadMovieDetails() {
        val movie = arguments?.getString(MOVIE_ID) ?: ""
        detailViewModel.getMovieDetails(movie)
    }

    private fun initViews() {
        binding.error.setOnClickListener { loadMovieDetails() }
        binding.close.setOnClickListener {
            Navigation.findNavController(binding.close).popBackStack()
        }
    }

    private fun initObservers() {
        detailViewModel.movie.observe(viewLifecycleOwner, {
            binding.movie = it
            pictureLoader.loadOriginalPictureFromToken(binding.moviePoster, it?.posterToken)
        })
        detailViewModel.isError.observe(viewLifecycleOwner, { isError ->
            binding.error.visibility = if (isError) View.VISIBLE else View.GONE
        })
        detailViewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            binding.progress.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}