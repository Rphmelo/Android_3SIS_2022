package br.com.fiap.marvelapp.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {

    @GET("/v1/public/characters")
    suspend fun listCharacters(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("offset") orderBy: Int = 140
    ): Response<MarvelCharacterModel>

    @GET("/v1/public/characters/{characterId}/comics")
    suspend fun listComics(
        @Path("characterId") characterId: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): Response<MarvelComicModel>
}