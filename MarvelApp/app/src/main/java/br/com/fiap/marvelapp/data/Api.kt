package br.com.fiap.marvelapp.data

import br.com.fiap.marvelapp.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object Api {

    private const val BASE_URL = "https://gateway.marvel.com:443"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val marvelService: MarvelService = retrofit.create(
        MarvelService::class.java
    )

    private val timestamp = Date().time.toString()
    private val hash = Utils.md5(timestamp + BuildConfig.MARVEL_PRIVATE_KEY + BuildConfig.MARVEL_API_KEY)

    suspend fun listCharacters(): Response<MarvelCharacterModel> =
        withContext(Dispatchers.IO) {
            marvelService.listCharacters(
                timestamp,
                BuildConfig.MARVEL_API_KEY,
                hash
            )
        }

    suspend fun listComics(characterId: Int): Response<MarvelComicModel> =
        withContext(Dispatchers.IO) {
            marvelService.listComics(
                characterId,
                timestamp,
                BuildConfig.MARVEL_API_KEY,
                hash
            )
        }
}