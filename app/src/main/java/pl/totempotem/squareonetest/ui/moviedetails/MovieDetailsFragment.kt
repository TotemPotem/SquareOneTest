package pl.totempotem.squareonetest.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import pl.totempotem.squareonetest.databinding.MovieDetailsFragmentBinding


class MovieDetailsFragment: Fragment() {
    private lateinit var binding: MovieDetailsFragmentBinding
    private val viewModel: MovieDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.movie.observe(viewLifecycleOwner, Observer {
            if (it!=null) {
                Picasso.with(view.context).load(it.poster).into(binding.poster)
                binding.title.text = it.title
                binding.year.text = it.year.toString()
                binding.categories.text = it.categories
                binding.runtime.text = it.runtime
                binding.language.text = it.language
                binding.synopsis.text = it.synopsis
                binding.score.text = it.score
                binding.votes.text = it.votes
                binding.boxoffice.text = it.boxOffice
                binding.director.text = it.director
                binding.writer.text = it.writer
                binding.actors.text = it.actors

            }
        })

        arguments?.getString("id")?.let {
            viewModel.fetch(it)
        }
    }

}