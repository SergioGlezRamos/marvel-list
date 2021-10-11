package com.marvellist.ui.about

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marvellist.base.BaseViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein

class AboutViewModel(context: Context) : BaseViewModel(), KodeinAware {

    override val kodein: Kodein by closestKodein(context)

}