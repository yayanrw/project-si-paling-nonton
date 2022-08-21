package com.heyproject.sipalingnonton.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.heyproject.sipalingnonton.domain.model.Genre

data class GenreDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
) {
    fun toGenre(): Genre {
        return Genre(id = id, name = name)
    }
}