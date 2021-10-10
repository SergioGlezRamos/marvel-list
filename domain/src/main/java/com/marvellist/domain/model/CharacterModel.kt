package com.marvellist.domain.model

class CharacterModel(
    val id: Int?,
    val name: String?,
    val description: String?,
    val modified: String?,
    val thumbnail: ThumbnailModel?,
    val resourceURI: String?,
    val comics: ComicModel?,
    val series: SerieModel?,
    val stories: StoryModel?,
    val events: EventModel?,
    val urls: List<UrlModel>
)