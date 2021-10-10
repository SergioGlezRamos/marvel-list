package com.marvellist.ui.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.marvellist.base.BaseViewModel
import com.marvellist.domain.model.RequestCharacterModel
import com.marvellist.domain.model.ResponseCharacterModel
import com.marvellist.domain.usecase.GetCharacterByIdUseCase
import com.marvellist.domain.usecase.GetCharacterListUseCase
import com.marvellist.manager.ConcurrencyManager
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class MainViewModel(context: Context) : BaseViewModel(), KodeinAware {

    var dataLoading: MutableLiveData<String> = MutableLiveData()

    override val kodein: Kodein by closestKodein(context)

    private val getCharacterByIdUseCase: GetCharacterByIdUseCase by instance()
    private val getCharacterListUseCase: GetCharacterListUseCase by instance()

    fun getCharacterById(id: String){
        executeUseCaseWithException(
            {
                val character = getCharacterByIdUseCase.execute(RequestCharacterModel(id))
                val characterName = character.data.results[0].name
            },
            { error->
                Log.d("Exception",error.message!!)
            }
        )
    }

    fun getCharacterList(limit: Int){
        executeUseCaseWithException(
            {
                val characterList = getCharacterListUseCase.execute(limit)
                val count = characterList.data.results.count()
                Log.d("ok","ok")
            },
            { error ->
                Log.d("Exception",error.message!!)
            }
        )
    }
}