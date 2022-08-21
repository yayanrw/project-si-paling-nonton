package com.heyproject.sipalingnonton.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.heyproject.sipalingnonton.data.util.JsonParser
import com.heyproject.sipalingnonton.domain.model.Cast
import com.heyproject.sipalingnonton.domain.model.Genre

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromCastJson(json: String): List<Cast> {
        return jsonParser.fromJson<ArrayList<Cast>>(
            json,
            object : TypeToken<ArrayList<Cast>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toCastJson(cast: List<Cast>): String {
        return jsonParser.toJson(
            cast,
            object : TypeToken<ArrayList<Cast>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromGenreJson(json: String): List<Genre> {
        return jsonParser.fromJson<ArrayList<Genre>>(
            json,
            object : TypeToken<ArrayList<Genre>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toGenreJson(cast: List<Genre>): String {
        return jsonParser.toJson(
            cast,
            object : TypeToken<ArrayList<Genre>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromIntJson(json: String): List<Int> {
        return jsonParser.fromJson<ArrayList<Int>>(
            json,
            object : TypeToken<ArrayList<Int>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toIntJson(cast: List<Int>): String {
        return jsonParser.toJson(
            cast,
            object : TypeToken<ArrayList<Int>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromStringJson(json: String): List<String> {
        return jsonParser.fromJson<ArrayList<String>>(
            json,
            object : TypeToken<ArrayList<String>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toStringJson(cast: List<String>): String {
        return jsonParser.toJson(
            cast,
            object : TypeToken<ArrayList<String>>() {}.type
        ) ?: "[]"
    }
}