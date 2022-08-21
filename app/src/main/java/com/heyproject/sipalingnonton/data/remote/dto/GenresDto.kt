package com.heyproject.sipalingnonton.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.heyproject.sipalingnonton.domain.model.Genres

data class GenresDto(
    @SerializedName("genres")
    val genres: List<GenreDto>
) {
    fun toGenres(): Genres {
        return Genres(genres = genres.map { it.toGenre() })
    }
}