package com.heyproject.sipalingnonton.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.heyproject.sipalingnonton.data.local.entity.PersonEntity

@Dao
interface PersonDao {
    @Query("SELECT * FROM persons WHERE id = :id")
    suspend fun getPerson(id: Int): PersonEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: PersonEntity)

    @Query("DELETE FROM persons WHERE created_at <= (strftime('%s','now', '-30 day') * 1000)")
    suspend fun deletePersons()
}