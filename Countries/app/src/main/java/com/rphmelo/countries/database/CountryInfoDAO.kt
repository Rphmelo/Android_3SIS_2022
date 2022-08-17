package com.rphmelo.countries.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete

@Dao
interface CountryInfoDAO {
    @Query("SELECT * FROM $COUNTRY_INFO_TABLE_NAME ORDER BY name ASC")
    fun getAll(): List<CountryInfo>

    @Insert
    fun insert(vararg countryInfo: CountryInfo)
}