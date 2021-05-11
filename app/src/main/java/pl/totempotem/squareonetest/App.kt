package pl.totempotem.squareonetest

import android.app.Application
import pl.totempotem.squareonetest.repository.MovieService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {
    companion object {
        val repositoryApi by lazy {
            Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieService::class.java)
        }
    }

}