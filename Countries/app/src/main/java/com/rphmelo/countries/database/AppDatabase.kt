package com.rphmelo.countries.database

import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

const val COUNTRY_DATABASE_NAME = "country_database"

@Database(entities = [CountryInfo::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract  fun countryDao(): CountryDAO

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    COUNTRY_DATABASE_NAME
                )
                .allowMainThreadQueries()
                .build()
                INSTANCE = instance
                instance
            }
        }

        const val SHARED_PREFERENCES_NAME = "COUNTRY_PREFERENCES"
        fun getSharedPreferences(activity: Activity): SharedPreferences {
            return activity.getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE)
        }
    }
}