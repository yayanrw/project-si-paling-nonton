package com.heyproject.sipalingnonton.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.heyproject.sipalingnonton.domain.model.BelongsToCollection

data class BelongsToCollectionDto(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String
) {
    fun toBelongsToCollection(): BelongsToCollection {
        return BelongsToCollection(
            backdropPath = backdropPath, id = id, name = name, posterPath = posterPath
        )
    }
}