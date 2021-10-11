package com.marvellist.ui.character.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marvellist.INT_ZERO
import com.marvellist.databinding.ItemCharacterBinding
import com.marvellist.domain.model.CharacterModel

class CharacterListAdapter(private val characterList: MutableList<CharacterModel> = mutableListOf(),private val onCharClick: (CharacterModel) -> Unit) : RecyclerView.Adapter<CharacterListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return CharacterListViewHolder(binding,parent.context)
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        val item = characterList[position]
        holder.render(item, onCharClick)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    fun add(newCharacterList: List<CharacterModel>) {
        characterList.addAll(newCharacterList)
        notifyDataSetChanged()
    }

    fun clear() {
        characterList.clear()
    }
}

class CharacterListViewHolder  (private val binding: ItemCharacterBinding, private val context: Context): RecyclerView.ViewHolder(binding.root) {

    fun render(item: CharacterModel, onCharClick: (CharacterModel) -> Unit) {
        binding.button.text = item.name
        binding.button.setOnClickListener {
            onCharClick(item)
        }
    }
}