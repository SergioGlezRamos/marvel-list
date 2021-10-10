package com.marvellist.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marvellist.ui.main.MainViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(private val context: Context): ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
      return MainViewModel(context) as T
      }
//    } else if(modelClass.isAssignableFrom(SplashViewModel::class.java)) {
//      return SplashViewModel(context) as T
//    }else if(modelClass.isAssignableFrom(RandomJokeViewModel::class.java)) {
//      return RandomJokeViewModel(context) as T
//    }
    throw IllegalArgumentException("Unknown class name")
  }
}