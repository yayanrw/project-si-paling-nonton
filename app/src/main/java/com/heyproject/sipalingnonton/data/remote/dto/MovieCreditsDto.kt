package com.heyproject.sipalingnonton.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.heyproject.sipalingnonton.domain.model.MovieCredits

data class MovieCreditsDto(
    @SerializedName("cast")
    val cast: List<CastDto>,
    @SerializedName("crew")
    val crew: List<CrewDto>,
    @SerializedName("id")
    val id: Int
) {
    fun toMovieCredits(): MovieCredits {
        return MovieCredits(
            cast = cast.map { it.toCast() },
            id = id
        )
    }
}