package pl.totempotem.squareonetest.ui.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import pl.totempotem.squareonetest.databinding.MovieListItemBinding
import pl.totempotem.squareonetest.model.Movie


class MoviesAdapter(private val listener: MovieClickListener): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    private val movies = ArrayList<Movie>()

    interface MovieClickListener {
        fun onMovieClick(id: String)
    }

    class ViewHolder(private val binding: MovieListItemBinding, private val listener: MovieClickListener): RecyclerView.ViewHolder(binding.root) {
        private lateinit var movie: Movie

        fun bind(movie: Movie) {
            this.movie = movie
            binding.root.setOnClickListener { listener.onMovieClick(movie.id) }
            binding.title.text = movie.title
            Picasso.with(itemView.context).load(movie.poster).into(binding.poster)
        }
    }

    override fun getItemCount(): Int = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun setItems(items: List<Movie>) {
        this.movies.clear()
        addItems(items)
    }

    fun addItems(items: List<Movie>) {
        this.movies.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.movies.clear()
        notifyDataSetChanged()
    }
}