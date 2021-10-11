package com.marvellist.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marvellist.ui.character.CharacterViewModel
import com.marvellist.ui.characterdetail.CharacterDetailViewModel
import com.marvellist.ui.about.AboutViewModel
import com.marvellist.ui.main.MainViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(private val context: Context): ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return when {
        modelClass.isAssignableFrom(MainViewModel::class.java) -> {
          MainViewModel(context) as T
        }
        modelClass.isAssignableFrom(CharacterViewModel::class.java) -> {
          CharacterViewModel(context) as T
        }
        modelClass.isAssignableFrom(AboutViewModel::class.java) -> {
          AboutViewModel(context) as T
        }
        modelClass.isAssignableFrom(CharacterDetailViewModel::class.java) -> {
            CharacterDetailViewModel(context) as T
        }
        else -> throw IllegalArgumentException("Unknown class name")
    }
  }
}