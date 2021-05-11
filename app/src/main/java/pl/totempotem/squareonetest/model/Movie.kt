package pl.totempotem.squareonetest.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("imdbID") val id: String,
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: Int,
    @SerializedName("Language") val language: String,
    @SerializedName("Poster")  val poster: String,
    @SerializedName("Director")  val director: String,
    @SerializedName("Writer")  val writer: String,
    @SerializedName("Actors")  val actors: String,
    @SerializedName("Genre")  val categories: String,
    @SerializedName("Plot")  val synopsis: String,
    @SerializedName("imdbRating")  val score: String,
    @SerializedName("imdbVotes")  val votes: String,
    @SerializedName("BoxOffice")  val boxOffice: String,
    @SerializedName("Runtime")  val runtime: String) {

    fun hasPoster(): Boolean = poster!="N/A"
}
