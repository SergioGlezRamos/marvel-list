package com.marvellist.ui.characterdetail

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.marvellist.base.BaseViewModel
import com.marvellist.domain.model.RequestCharacterModel
import com.marvellist.domain.model.ResponseCharacterModel
import com.marvellist.domain.usecase.GetCharacterByIdUseCase
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class CharacterDetailViewModel(context: Context) : BaseViewModel(), KodeinAware {

    val characterListResponse: MutableLiveData<ResponseCharacterModel?> by lazy {
        MutableLiveData<ResponseCharacterModel?>()
    }

    val loadingState: MutableLiveData<Boolean?> by lazy {
        MutableLiveData<Boolean?>()
    }

    override val kodein: Kodein by closestKodein(context)

    private val getCharacterByIdUseCase: GetCharacterByIdUseCase by instance()

    fun getCharacterById(id: String){
        executeUseCaseWithException(
            {
                loadingState.value = true
                val characterList = getCharacterByIdUseCase.execute(RequestCharacterModel(id))
                loadingState.value = false
                characterListResponse.value = characterList
            },
            { error ->
                Log.d("Exception",error.message!!)
            }
        )
    }
}