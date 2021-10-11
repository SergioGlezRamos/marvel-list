package com.marvellist.ui.character

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marvellist.CHARACTER_LIST_REQUEST_LIMIT
import com.marvellist.INT_ZERO
import com.marvellist.base.BaseViewModel
import com.marvellist.domain.model.RequestCharacterListModel
import com.marvellist.domain.model.RequestCharacterModel
import com.marvellist.domain.model.ResponseCharacterModel
import com.marvellist.domain.usecase.GetCharacterByIdUseCase
import com.marvellist.domain.usecase.GetCharacterListUseCase
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class CharacterViewModel(context: Context) : BaseViewModel(), KodeinAware {

    val characterListResponse: MutableLiveData<ResponseCharacterModel?> by lazy {
        MutableLiveData<ResponseCharacterModel?>()
    }

    val loadingState: MutableLiveData<Boolean?> by lazy {
        MutableLiveData<Boolean?>()
    }

    override val kodein: Kodein by closestKodein(context)

    private val getCharacterByIdUseCase: GetCharacterByIdUseCase by instance()
    private val getCharacterListUseCase: GetCharacterListUseCase by instance()

    fun getCharacterList(limit: Int = CHARACTER_LIST_REQUEST_LIMIT, offset: Int = INT_ZERO){
        executeUseCaseWithException(
            {
                loadingState.value = true
                val characterList = getCharacterListUseCase.execute(RequestCharacterListModel(limit,offset))
                loadingState.value = false
                characterListResponse.value = characterList
            },
            { error ->
                Log.d("Exception",error.message!!)
            }
        )
    }
}