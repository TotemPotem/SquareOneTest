package pl.totempotem.squareonetest.ui.movielist

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.totempotem.squareonetest.repository.Repository
import pl.totempotem.squareonetest.model.Movies


class MovieListViewModel: ViewModel() {
    var movies: LiveData<Movies>
    private val phrase = MutableLiveData<String>()
    private val page = MutableLiveData<Int>()

    init {
        val mediator = MediatorLiveData<Pair<String?, Int?>>().apply {
            addSource(phrase) { value = Pair(it, page.value) }
            addSource(page) { value = Pair(phrase.value, it) }
        }
        movies = Transformations.switchMap(mediator) { pair ->
            if (pair.first != null && pair.second != null) {
                fetchMovies(pair.first!!, pair.second!!)
            } else null
        }
    }

    private fun fetchMovies(query: String, page: Int): LiveData<Movies> {
        val liveData = MutableLiveData<Movies>()
        viewModelScope.launch(Dispatchers.IO) {
            val response = Repository.getMovieList(query, page)
            response.page = page
            liveData.postValue(response)
        }
        return liveData
    }

    fun fetch(query: String) {
        this.phrase.value = query
        this.page.value = 1
    }

    fun fetchNext() {
        movies.value?.movies?.let {
            if (it.size<movies.value!!.total) {
                page.value = page.value!! + 1
            }
        }
    }

}