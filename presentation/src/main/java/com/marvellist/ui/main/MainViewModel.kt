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
}