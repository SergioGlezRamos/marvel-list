package com.marvellist.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.marvellist.R
import com.marvellist.base.BaseFragment
import com.marvellist.databinding.FragmentAboutBinding
import org.kodein.di.generic.instance

class AboutFragment : BaseFragment<FragmentAboutBinding, AboutViewModel>() {

    override fun layoutId(): Int = R.layout.fragment_about

    override fun getViewModelClass(): Class<AboutViewModel> = AboutViewModel::class.java

    private val viewModelFactory: ViewModelProvider.Factory by instance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAboutBinding.inflate(inflater, container, false)
        val root: View = binding.root
        viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModelClass())

        val textView: TextView = binding.textAbout

        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return root
    }
}