package pl.totempotem.squareonetest.repository

import pl.totempotem.squareonetest.model.Movie
import pl.totempotem.squareonetest.model.Movies
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("/")
    suspend fun getList(@Query("apikey") apikey: String, @Query("s") phrase: String, @Query("page") page: Int, @Query("type") type: String = "movie") : Movies

    @GET("/")
    suspend fun getMovie(@Query("apikey") apikey: String, @Query("i") id: String): Movie
}
