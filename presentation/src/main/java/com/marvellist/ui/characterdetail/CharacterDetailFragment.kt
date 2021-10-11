package com.marvellist.ui.characterdetail

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marvellist.*
import com.marvellist.base.BaseFragment
import com.marvellist.databinding.FragmentCharacterDetailBinding
import com.marvellist.domain.model.CharacterModel
import com.marvellist.domain.model.ElementModel
import com.marvellist.ui.characterdetail.adapter.ElementListAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.kodein.di.generic.instance
import java.lang.Exception


class CharacterDetailFragment : BaseFragment<FragmentCharacterDetailBinding, CharacterDetailViewModel>() {

    override fun layoutId(): Int = R.layout.fragment_character_detail

    override fun getViewModelClass(): Class<CharacterDetailViewModel> = CharacterDetailViewModel::class.java

    private var characterId: Int? = null
    private var newComicListRequested: Boolean = false
    private var newEventListRequested: Boolean = false
    private var newSerieListRequested: Boolean = false
    private var newStoryListRequested: Boolean = false
    private var totalStories: Int = INT_ZERO
    private var totalSeries: Int = INT_ZERO
    private var totalEvents: Int = INT_ZERO
    private var totalComics: Int = INT_ZERO
    private var currentComicOffset = INT_ZERO
    private var currentEventOffset = INT_ZERO
    private var currentSerieOffset = INT_ZERO
    private var currentStoryOffset = INT_ZERO
    private val viewModelFactory: ViewModelProvider.Factory by instance()
    val args: CharacterDetailFragmentArgs by navArgs()

    private val comicListAdapter: ElementListAdapter by lazy {
        ElementListAdapter() {
            createDialog(it)
        }
    }

    private val eventListAdapter: ElementListAdapter by lazy {
        ElementListAdapter() {
            createDialog(it)
        }
    }

    private val serieListAdapter: ElementListAdapter by lazy {
        ElementListAdapter() {
            createDialog(it)
        }
    }

    private val storyListAdapter: ElementListAdapter by lazy {
        ElementListAdapter() {
            createDialog(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModelClass())

        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupObservers()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterId = args.characterModel.id
        viewModel.getCharacterComics(characterId)
        viewModel.getCharacterEvents(characterId)
        viewModel.getCharacterSeries(characterId)
        viewModel.getCharacterStories(characterId)
        setupView(args.characterModel)
    }

    private fun createDialog(element: ElementModel){
        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }

        if(!element.description.isNullOrEmpty() && !element.title.isNullOrEmpty()){
            builder?.setMessage(element.description)
                ?.setTitle(element.title)
        } else {
            builder?.setMessage(R.string.character_detail_description_not_found)
                ?.setTitle(element.title)
        }

        val dialog: AlertDialog? = builder?.create()
        dialog?.show()
    }

    private fun setupObservers() {
        viewModel.comicListResponse.observe(viewLifecycleOwner, Observer {
            it?.also {
                totalComics = it.data.total?: INT_ZERO
                if(newComicListRequested){
                    updateRecycler(it.data.results,binding.rvComic)
                    newComicListRequested = false
                } else {
                    if(it.data.results.size == INT_ZERO){
                        binding.rvComic.visibility = View.GONE
                        binding.tvComicTitle.visibility = View.GONE
                    } else {
                        binding.rvComic.visibility = View.VISIBLE
                        binding.tvComicTitle.visibility = View.VISIBLE
                        setupRecycler(it.data.results,binding.rvComic, COMIC_ELEMENT)
                    }
                }
            }
        })

        viewModel.eventListResponse.observe(viewLifecycleOwner, Observer {
            it?.also {
                totalEvents = it.data.total?: INT_ZERO
                if(newEventListRequested){
                    updateRecycler(it.data.results,binding.rvEvent)
                    newEventListRequested = false
                } else {
                    if(it.data.results.size == INT_ZERO){
                        binding.rvEvent.visibility = View.GONE
                        binding.tvEventTitle.visibility = View.GONE
                    } else {
                        binding.rvEvent.visibility = View.VISIBLE
                        binding.tvEventTitle.visibility = View.VISIBLE
                        setupRecycler(it.data.results, binding.rvEvent, EVENT_ELEMENT)
                    }
                }
            }
        })

        viewModel.serieListResponse.observe(viewLifecycleOwner, Observer {
            it?.also {
                totalSeries = it.data.total?: INT_ZERO
                if(newSerieListRequested){
                    updateRecycler(it.data.results,binding.rvSerie)
                    newSerieListRequested = false
                } else {
                    if(it.data.results.size == INT_ZERO){
                        binding.rvSerie.visibility = View.GONE
                        binding.tvSerieTitle.visibility = View.GONE
                    } else {
                        binding.rvSerie.visibility = View.VISIBLE
                        binding.tvSerieTitle.visibility = View.VISIBLE
                        setupRecycler(it.data.results, binding.rvSerie, SERIE_ELEMENT)
                    }
                }
            }
        })

        viewModel.storyListResponse.observe(viewLifecycleOwner, Observer {
            it?.also {
                totalStories = it.data.total?: INT_ZERO
                if(newStoryListRequested){
                    updateRecycler(it.data.results,binding.rvStory)
                    newStoryListRequested = false
                } else {
                    if(it.data.results.size == INT_ZERO){
                        binding.rvStory.visibility = View.GONE
                        binding.tvStoryTitle.visibility = View.GONE
                    } else {
                        binding.rvStory.visibility = View.VISIBLE
                        binding.tvStoryTitle.visibility = View.VISIBLE
                        setupRecycler(it.data.results, binding.rvStory, STORY_ELEMENT)
                    }
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

    private fun setupView(characterModel: CharacterModel) {
        binding.tvName.text = characterModel.name

        val imageURL = characterModel.thumbnail?.path + "." + characterModel.thumbnail?.extension
        val picasso = Picasso.Builder(requireContext()).build()
        picasso.load(imageURL)
            .error(R.drawable.ic_placeholder)
            .into(binding.ivDetailHeader, object : Callback{
                override fun onSuccess() {
                    binding.progressBarCharacterImage.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    binding.progressBarCharacterImage.visibility = View.GONE
                    e?.message?.also {
                        Log.d("Exception", it)
                    }
                }

            })

        if(!characterModel.description.isNullOrEmpty()) {
            binding.tvDescriptionText.visibility = View.VISIBLE
            binding.tvDescriptionTitle.visibility = View.VISIBLE
            binding.tvDescriptionText.text = characterModel.description
        } else {
            binding.tvDescriptionText.visibility = View.GONE
            binding.tvDescriptionTitle.visibility = View.GONE
        }
    }

    private fun updateRecycler(elementList: List<ElementModel>, rv: RecyclerView){
        (rv.adapter as ElementListAdapter).add(elementList)
    }

    private fun setupRecycler(elementList: List<ElementModel>, rv :RecyclerView, type: String){
        rv.layoutManager = LinearLayoutManager(requireContext(),
            RecyclerView.HORIZONTAL,false)
        when(type){
            COMIC_ELEMENT->{
                if(comicListAdapter.itemCount == INT_ZERO)
                    comicListAdapter.add(elementList as MutableList<ElementModel>)
                rv.adapter = comicListAdapter

                rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)
                        if (!recyclerView.canScrollHorizontally(1)) {
                            if(!newComicListRequested) {
                                if(currentComicOffset < totalComics) {
                                    currentComicOffset += ELEMENT_LIST_REQUEST_LIMIT
                                    viewModel.getCharacterComics(characterId ,offset = currentComicOffset)
                                    newComicListRequested = true
                                }
                            }
                        }
                    }
                })
            }
            EVENT_ELEMENT->{
                if(eventListAdapter.itemCount == INT_ZERO)
                    eventListAdapter.add(elementList as MutableList<ElementModel>)
                rv.adapter = eventListAdapter

                rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)
                        if (!recyclerView.canScrollHorizontally(1)) {
                            if(!newEventListRequested) {
                                if(currentEventOffset < totalEvents) {
                                    currentEventOffset += ELEMENT_LIST_REQUEST_LIMIT
                                    viewModel.getCharacterEvents(characterId ,offset = currentEventOffset)
                                    newEventListRequested = true
                                }
                            }
                        }
                    }
                })
            }
            SERIE_ELEMENT->{
                if(serieListAdapter.itemCount == INT_ZERO)
                    serieListAdapter.add(elementList as MutableList<ElementModel>)
                rv.adapter = serieListAdapter

                rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)
                        if (!recyclerView.canScrollHorizontally(1)) {
                            if(!newSerieListRequested) {
                                if(currentSerieOffset < totalSeries) {
                                    currentSerieOffset += ELEMENT_LIST_REQUEST_LIMIT
                                    viewModel.getCharacterSeries(characterId ,offset = currentSerieOffset)
                                    newSerieListRequested = true
                                }
                            }
                        }
                    }
                })
            }
            STORY_ELEMENT->{
                if(storyListAdapter.itemCount == INT_ZERO)
                    storyListAdapter.add(elementList as MutableList<ElementModel>)
                rv.adapter = storyListAdapter

                rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)
                        if (!recyclerView.canScrollHorizontally(1)) {
                            if(!newStoryListRequested) {
                                if(currentStoryOffset < totalStories) {
                                    currentStoryOffset += ELEMENT_LIST_REQUEST_LIMIT
                                    viewModel.getCharacterStories(characterId ,offset = currentStoryOffset)
                                    newStoryListRequested = true
                                }
                            }
                        }
                    }
                })
            }
        }
    }

}