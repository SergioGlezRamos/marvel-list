package com.marvellist.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marvellist.CHARACTER_LIST_REQUEST_LIMIT
import com.marvellist.INT_ZERO
import com.marvellist.R
import com.marvellist.base.BaseFragment
import com.marvellist.databinding.FragmentCharacterListBinding
import com.marvellist.domain.model.CharacterModel
import com.marvellist.ui.character.adapter.CharacterListAdapter
import org.kodein.di.generic.instance


class CharacterListFragment : BaseFragment<FragmentCharacterListBinding,CharacterViewModel>() {

    override fun layoutId(): Int = R.layout.fragment_character_list

    override fun getViewModelClass(): Class<CharacterViewModel> = CharacterViewModel::class.java

    private val viewModelFactory: ViewModelProvider.Factory by instance()
    private var newListRequested = false
    private var currentOffset = INT_ZERO
    private var totalCharacters = INT_ZERO

    private val characterListAdapter: CharacterListAdapter by lazy {
        CharacterListAdapter() {
            val action = CharacterListFragmentDirections.actionNavCharacterListToNavCharDetail(it)
            requireView().findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModelClass())

        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupObservers()
        return root
    }

    private fun setupObservers() {
        viewModel.characterListResponse.observe(viewLifecycleOwner, Observer {
            it?.also {
                totalCharacters = it.data.total?: INT_ZERO
                if(newListRequested){
                    updateRecycler(it.data.results)
                    newListRequested = false
                } else {
                    setupRecycler(it.data.results)
                }
            }
        })

        viewModel.loadingState.observe(viewLifecycleOwner, Observer {
            it?.also {
                if(it)
                    binding.clProgressBarContainer.visibility = View.VISIBLE
                else
                    binding.clProgressBarContainer.visibility = View.GONE
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(totalCharacters == INT_ZERO) {
            binding.rvCharacters.adapter?.also {
                (binding.rvCharacters.adapter as CharacterListAdapter).clear()
            }
            viewModel.getCharacterList()
        }
    }

    private fun updateRecycler(characterList: List<CharacterModel>){
        (binding.rvCharacters.adapter as CharacterListAdapter).add(characterList)
    }

    private fun setupRecycler(characterList: List<CharacterModel>){
        binding.rvCharacters.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        if(characterListAdapter.itemCount == INT_ZERO)
            characterListAdapter.add(characterList as MutableList<CharacterModel>)
        binding.rvCharacters.adapter = characterListAdapter

        binding.rvCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    if(!newListRequested) {
                        if(currentOffset < totalCharacters) {
                            currentOffset += CHARACTER_LIST_REQUEST_LIMIT
                            viewModel.getCharacterList(offset = currentOffset)
                            newListRequested = true
                        }
                    }
                }
            }
        })
    }
}