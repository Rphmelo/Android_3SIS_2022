package com.rphmelo.countries.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CountryDAO {

    @Query("SELECT * FROM $COUNTRY_INFO_TABLE_NAME ORDER BY name ASC")
    fun getAll(): List<CountryInfo>

    @Insert
    fun insert(countryInfo: CountryInfo)
}