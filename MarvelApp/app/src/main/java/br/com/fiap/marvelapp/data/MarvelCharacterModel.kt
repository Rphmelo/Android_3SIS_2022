package br.com.fiap.marvelapp.data

import com.google.gson.annotations.SerializedName

data class MarvelCharacterModel (
     @SerializedName("data")
     val data: MarvelCharacterDataModel?
)

data class MarvelCharacterDataModel (
     @SerializedName("results")
     val results: List<MarvelCharacterDataResultModel>?
)

data class MarvelCharacterDataResultModel (
     @SerializedName("id")
     val id: Int?,
     @SerializedName("name")
     val name: String?,
     @SerializedName("description")
     val description: String?,
     @SerializedName("thumbnail")
     val thumbnail: MarvelCharacterDataResultThumbnailModel?,
)

data class MarvelCharacterDataResultThumbnailModel(
     @SerializedName("path")
     val path: String?,
     @SerializedName("extension")
     val extension: String?
)