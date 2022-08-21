package com.heyproject.sipalingnonton.domain.model

data class Person(
    val alsoKnownAs: List<String>,
    val biography: String,
    val birthday: String,
    val deathday: String?,
    val id: Int,
    val imdbId: String,
    val knownForDepartment: String,
    val name: String,
    val placeOfBirth: String,
    val profilePath: String
)