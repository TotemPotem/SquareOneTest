package pl.totempotem.squareonetest.ui.movielist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import pl.totempotem.squareonetest.R
import pl.totempotem.squareonetest.databinding.MovieListFragmentBinding
import pl.totempotem.squareonetest.model.Movies
import pl.totempotem.squareonetest.ui.hideKeyboard


class MovieListFragment: Fragment(), MoviesAdapter.MovieClickListener {
    companion object {
        private const val TAG = "MovieListFragment"
    }


    private lateinit var binding: MovieListFragmentBinding
    private lateinit var adapter: MoviesAdapter
    private val viewModel: MovieListViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = MoviesAdapter(this)
        viewModel.movies.observe(this, Observer(this::bindResult))
    }

    private fun bindResult(result: Movies) {
            if (result.response) {
                binding.empty.visibility = View.GONE
                if (result.page==1) adapter.setItems(result.movies)
                else adapter.addItems(result.movies)
            }
            else if (result.page==1) {
                adapter.clearItems()
                binding.empty.visibility = View.VISIBLE
            }
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

        binding = MovieListFragmentBinding.inflate(inflater, container, false)
        binding.movieList.adapter = adapter
        binding.movieList.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN)) {
                    Log.d(TAG, "reached end of scroll area")
                    viewModel.fetchNext()
                }
            }
        })
        binding.searchField.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                search()
                true
            }
            else false
        }
        binding.searchButton.setOnClickListener { search() }

        return binding.root
    }

    private fun search() {
        val phrase = binding.searchField.text.toString().trim()
        if (phrase.isNotEmpty()) {
            view?.let { activity?.hideKeyboard(it) }
            viewModel.fetch(phrase)
        }
    }

    override fun onMovieClick(id: String) {
        findNavController().navigate(
            R.id.action_toMovieDetails,
            bundleOf("id" to id)
        )
    }
}