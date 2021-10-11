package com.marvellist.domain.model

import java.io.Serializable

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
) : Serializable