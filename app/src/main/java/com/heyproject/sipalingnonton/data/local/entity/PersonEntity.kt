package com.heyproject.sipalingnonton.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.heyproject.sipalingnonton.domain.model.Person

@Entity(tableName = "persons")
data class PersonEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "also_known_as")
    val alsoKnownAs: List<String>,

    @ColumnInfo(name = "biography")
    val biography: String,

    @ColumnInfo(name = "birthday")
    val birthday: String,

    @ColumnInfo(name = "deathday")
    val deathday: String,

    @ColumnInfo(name = "place_of_birth")
    val placeOfBirth: String,

    @ColumnInfo(name = "profile_path")
    val profilePath: String,

    @ColumnInfo(name = "imdb_id")
    val imdbId: String,

    @ColumnInfo(name = "known_for_department")
    val knownForDepartment: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
) {
    fun toPerson(): Person {
        return Person(
            alsoKnownAs = alsoKnownAs,
            biography = biography,
            birthday = birthday,
            deathday = deathday,
            id = id,
            imdbId = imdbId,
            knownForDepartment = knownForDepartment,
            name = name,
            placeOfBirth = placeOfBirth,
            profilePath = profilePath
        )
    }
}
