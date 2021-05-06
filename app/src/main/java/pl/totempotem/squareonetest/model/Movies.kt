package pl.totempotem.squareonetest.model

import com.google.gson.annotations.SerializedName

class Movies(@SerializedName("Response") val response: Boolean, @SerializedName("totalResults") val total: Int, @SerializedName("Search") val movies: List<Movie>, var page: Int = 1) {
}