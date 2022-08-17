package com.rphmelo.countries.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

const val COUNTRY_DATABASE_NAME = "country_database"

@Database(entities = [CountryInfo::class], version = 1)
abstract class AppDatabase: RoomDatabase(){

    abstract fun countryInfoDao(): CountryInfoDAO

    companion object {
        /*
        A razão pela qual estamos tornando nossa variável INSTANCE volátil (@Volatile) é para que seu valor nunca
        seja armazenado em cache e todas as operações de leitura e gravação sejam feitas diretamente na
        memória principal. Isso garante que a integridade dos dados (ou seja, quaisquer alterações feitas
        na instância do banco de dados) seja visível para todas as outras threads imediatamente e evita,
        por exemplo, que duas threads atualizem a mesma entidade em um cache
        */
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    COUNTRY_DATABASE_NAME)
                    .allowMainThreadQueries() //Possibilita execução de operações de banco de dados na MainThread, não deve ser executado em produção
                    .fallbackToDestructiveMigration()//Habilita destruição do banco e criação de um novo a cada upgrade do modelo
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}