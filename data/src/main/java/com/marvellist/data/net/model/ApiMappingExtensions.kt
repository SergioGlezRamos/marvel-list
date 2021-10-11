package com.marvellist.data.net.model

import com.marvellist.domain.model.*

fun RequestCharacterModel.toDataModel(): RequestCharacterMarvelApi =
    RequestCharacterMarvelApi(
        id = id
    )

fun ResponseCharacterMarvelApi.toDomainModel(): ResponseCharacterModel =
    ResponseCharacterModel(
        code = code,
        status = status,
        copyRight = copyRight,
        attributionText = attributionText,
        attibutionHTML = attibutionHTML,
        etag = etag,
        data = data.toDomainModel()
    )

fun DataApiMarvelModel.toDomainModel(): CharacterDetailModel {

    val listResult = results.map {
        it.toDomainModel()
    }

    return CharacterDetailModel(
        offset = offset,
        limit = limit,
        total = total,
        count = count,
        results = listResult
    )
}


fun CharacterApiMarvelModel.toDomainModel(): CharacterModel {

    val listUrl = urls.map {
        it.toDomainModel()
    }

    return CharacterModel(
        id = id,
        name = name,
        description = description,
        modified = modified,
        thumbnail = thumbnail,
        resourceURI = resourceURI,
        comics = comics?.toComicDomainModel(),
        series = series?.toSerieDomainModel(),
        stories = stories?.toStoryDomainModel(),
        events = events?.toEventDomainModel(),
        urls = listUrl
    )
}

fun ElementApiMarvelModel.toComicDomainModel(): ComicModel {

    val listItem = items.map {
        it.toDomainModel()
    }

    return ComicModel(
        available = available,
        returned = returned,
        collectionURI = collectionURI,
        items = listItem
    )
}

fun ElementApiMarvelModel.toSerieDomainModel(): SerieModel {
    val listItem = items.map {
        it.toDomainModel()
    }
    return SerieModel(
        available = available,
        returned = returned,
        collectionURI = collectionURI,
        items = listItem
    )
}

fun ElementApiMarvelModel.toStoryDomainModel(): StoryModel {
    val listItem = items.map {
        it.toDomainModel()
    }
    return StoryModel(
        available = available,
        returned = returned,
        collectionURI = collectionURI,
        items = listItem
    )
}

fun ElementApiMarvelModel.toEventDomainModel(): EventModel {
    val listItem = items.map {
        it.toDomainModel()
    }
    return EventModel(
        available = available,
        returned = returned,
        collectionURI = collectionURI,
        items = listItem
    )
}

fun UrlApiMarvelModel.toDomainModel(): UrlModel =
    UrlModel(
        type = type,
        url = url
    )

fun ItemApiMarvelModel.toDomainModel(): ItemModel =
    ItemModel(
        resourceURI = resourceURI,
        name = name,
        type = type
    )

fun ResponseElementByCharacterIdMarvelApi.toDomainModel(): ResponseElementByCharacterIdModel =
    ResponseElementByCharacterIdModel(
        code = code,
        status = status,
        copyRight = copyRight,
        attributionText = attributionText,
        attibutionHTML = attibutionHTML,
        etag = etag,
        data = data.toDomainModel()
    )

fun ElementsDetailApiMarvelModel.toDomainModel(): ElementsDetailModel {
    val listResult = results.map {
        it.toDomainModel()
    }

    return ElementsDetailModel(
        offset = offset,
        limit = limit,
        total = total,
        count = count,
        results = listResult
    )
}

fun ElementDetailApiMarvelModel.toDomainModel(): ElementModel =
    ElementModel(
        id = id,
        title = title,
        description = description,
        thumbnail = thumbnail
    )
