package pl.totempotem.squareonetest.repository

import pl.totempotem.squareonetest.App
import pl.totempotem.squareonetest.BuildConfig
import pl.totempotem.squareonetest.model.Movie
import pl.totempotem.squareonetest.model.Movies

object Repository {

    suspend fun getMovieList(query: String, page: Int): Movies {
        return App.repositoryApi.getList(
            BuildConfig.API_KEY, query, page)
    }

    suspend fun getMovieDetails(id: String): Movie {
        return App.repositoryApi.getMovie(
            BuildConfig.API_KEY, id)
    }

}