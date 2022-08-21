package com.heyproject.sipalingnonton.data.remote.dto


import com.google.gson.annotations.SerializedName

data class GenresDto(
    @SerializedName("genres")
    val genres: List<GenreDto>
)