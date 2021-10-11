package com.marvellist.ui.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.marvellist.R
import com.marvellist.base.BaseFragment
import com.marvellist.databinding.FragmentCharacterDetailBinding
import com.marvellist.domain.model.CharacterModel
import org.kodein.di.generic.instance

class CharacterDetailFragment : BaseFragment<FragmentCharacterDetailBinding, CharacterDetailViewModel>() {

    override fun layoutId(): Int = R.layout.fragment_character_detail

    override fun getViewModelClass(): Class<CharacterDetailViewModel> = CharacterDetailViewModel::class.java

    private val viewModelFactory: ViewModelProvider.Factory by instance()

    val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModelClass())

        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(args.characterModel)
    }

    private fun setupView(characterModel: CharacterModel) {
        binding.tvName.text = characterModel.name
    }


}