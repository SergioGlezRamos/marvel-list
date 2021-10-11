package com.marvellist.ui.characterdetail

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.marvellist.*
import com.marvellist.base.BaseViewModel
import com.marvellist.domain.model.RequestCharacterModel
import com.marvellist.domain.model.RequestElementsByCharacterIdModel
import com.marvellist.domain.model.ResponseCharacterModel
import com.marvellist.domain.model.ResponseElementByCharacterIdModel
import com.marvellist.domain.usecase.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class CharacterDetailViewModel(context: Context) : BaseViewModel(), KodeinAware {

    val comicListResponse: MutableLiveData<ResponseElementByCharacterIdModel?> by lazy {
        MutableLiveData<ResponseElementByCharacterIdModel?>()
    }

    val eventListResponse: MutableLiveData<ResponseElementByCharacterIdModel?> by lazy {
        MutableLiveData<ResponseElementByCharacterIdModel?>()
    }

    val serieListResponse: MutableLiveData<ResponseElementByCharacterIdModel?> by lazy {
        MutableLiveData<ResponseElementByCharacterIdModel?>()
    }

    val storyListResponse: MutableLiveData<ResponseElementByCharacterIdModel?> by lazy {
        MutableLiveData<ResponseElementByCharacterIdModel?>()
    }

    val loadingState: MutableLiveData<Boolean?> by lazy {
        MutableLiveData<Boolean?>()
    }

    override val kodein: Kodein by closestKodein(context)

    private val getElementsByCharacterIdUseCase: GetElementsByCharacterIdUseCase by instance()

    fun getCharacterComics(id: Int?,offset: Int = INT_ZERO){
        id?.also { id ->
            executeUseCaseWithException(
                {
                    loadingState.value = true
                    val comicList = getElementsByCharacterIdUseCase.execute(RequestElementsByCharacterIdModel(id,offset, COMIC_ELEMENT))
                    loadingState.value = false
                    comicListResponse.value = comicList
                },
                { error ->
                    Log.d("Exception",error.message!!)
                }
            )
        }
    }

    fun getCharacterEvents(id: Int?,offset: Int = INT_ZERO){
        id?.also { id ->
            executeUseCaseWithException(
                {
                    loadingState.value = true
                    val eventList = getElementsByCharacterIdUseCase.execute(RequestElementsByCharacterIdModel(id,offset, EVENT_ELEMENT))
                    loadingState.value = false
                    eventListResponse.value = eventList
                },
                { error ->
                    Log.d("Exception", error.message!!)
                }
            )
        }
    }

    fun getCharacterSeries(id: Int?,offset: Int = INT_ZERO){
        id?.also { id ->
            executeUseCaseWithException(
                {
                    loadingState.value = true
                    val serieList = getElementsByCharacterIdUseCase.execute(RequestElementsByCharacterIdModel(id,offset, SERIE_ELEMENT))
                    loadingState.value = false
                    serieListResponse.value = serieList
                },
                { error ->
                    Log.d("Exception", error.message!!)
                }
            )
        }
    }

    fun getCharacterStories(id: Int?,offset: Int = INT_ZERO){
        id?.also { id ->
            executeUseCaseWithException(
                {
                    loadingState.value = true
                    val storyList = getElementsByCharacterIdUseCase.execute(RequestElementsByCharacterIdModel(id,offset, STORY_ELEMENT))
                    loadingState.value = false
                    storyListResponse.value = storyList
                },
                { error ->
                    Log.d("Exception", error.message!!)
                }
            )
        }
    }

}