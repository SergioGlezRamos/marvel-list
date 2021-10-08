package com.marvellist.ui.main

import com.marvellist.base.BaseViewModel
import com.marvellist.domain.usecase.GetCharacterByIdUseCase

class MainViewModel(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
) : BaseViewModel() {
}