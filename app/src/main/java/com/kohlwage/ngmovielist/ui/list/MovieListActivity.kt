package com.kohlwage.ngmovielist.ui.list

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.kohlwage.ngmovielist.R
import com.kohlwage.ngmovielist.databinding.ActivityMovieListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@AndroidEntryPoint
class MovieListActivity : AppCompatActivity() {

    private val pageViewModel: MovieListViewModel by viewModels()
    private val movieAdapter by lazy { MovieListAdapter(pageViewModel::onMovieClick) }

    private lateinit var binding: ActivityMovieListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.todo_toolbar))
        binding.todoToolbar.title = title
        initViews()
        registerObservers()
    }

    private fun initViews() {
        binding.lifecycleOwner = this
        binding.viewModel = pageViewModel
        binding.todoRecycler.adapter = movieAdapter
    }

    private fun registerObservers() {
        this.lifecycleScope.launchWhenCreated {
            pageViewModel.movieList.collectLatest { pagingData ->
                Timber.tag("TASK").d(pagingData.toString())
                movieAdapter.submitData(pagingData)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}