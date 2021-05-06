package pl.totempotem.squareonetest.ui.moviedetails

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.totempotem.squareonetest.repository.Repository
import pl.totempotem.squareonetest.model.Movie

class MovieDetailsViewModel: ViewModel() {

    var movie: LiveData<Movie>
    private val movieId = MutableLiveData<String>()

    init {
        movie = Transformations.switchMap(movieId) { fetchMovie(it) }
    }

    private fun fetchMovie(id: String): LiveData<Movie> {
        val liveData = MutableLiveData<Movie>()

        viewModelScope.launch(Dispatchers.IO) {
            val response = Repository.getMovieDetails(id)
            liveData.postValue(response)
        }
        return liveData
    }

    fun fetch(id: String) {
        movieId.value = id
    }
}