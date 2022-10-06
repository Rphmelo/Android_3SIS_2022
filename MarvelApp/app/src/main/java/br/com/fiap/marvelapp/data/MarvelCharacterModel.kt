package br.com.fiap.marvelapp.data

data class MarvelCharacterModel(
    val data: MarvelCharacterDataModel?
)

data class MarvelCharacterDataModel(
   val results: List<MarvelCharacterDataResultModel>?
)

data class MarvelCharacterDataResultModel(
   val id: Int?,
   val name: String?,
   val description: String?,
   val thumbnail: MarvelCharacterDataResultThumbnailModel?,
)

data class MarvelCharacterDataResultThumbnailModel(
   val path: String?,
   val extension: String?,
)
