package com.rphmelo.countries.database

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

const val COUNTRY_INFO_TABLE_NAME = "country"
@Entity(tableName = COUNTRY_INFO_TABLE_NAME)
@Parcelize
data class CountryInfo(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @NonNull @ColumnInfo val name: String,
    @NonNull @ColumnInfo val capital: String,
    @NonNull @ColumnInfo val language: String,
    @NonNull @ColumnInfo val currency: String,
    @NonNull @ColumnInfo val location: String,
): Parcelable
