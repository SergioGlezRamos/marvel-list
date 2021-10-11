package com.marvellist.ui.characterdetail.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marvellist.R
import com.marvellist.databinding.ItemElementBinding
import com.marvellist.domain.model.ElementModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class ElementListAdapter(private val elementList: MutableList<ElementModel> = mutableListOf(),private val isStory: Boolean, private val onElementClick: (ElementModel) -> Unit) : RecyclerView.Adapter<ElementListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementListViewHolder {
        val binding = ItemElementBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ElementListViewHolder(binding,parent.context)
    }

    override fun onBindViewHolder(holder: ElementListViewHolder, position: Int) {
        val item = elementList[position]
        holder.render(item, isStory, onElementClick)
    }

    override fun getItemCount(): Int {
        return elementList.size
    }

    fun add(newElementList: List<ElementModel>) {
        elementList.addAll(newElementList)
        notifyDataSetChanged()
    }

    fun clear() {
        elementList.clear()
    }
}

class ElementListViewHolder  (private val binding: ItemElementBinding, private val context: Context): RecyclerView.ViewHolder(binding.root) {

    fun render(item: ElementModel, isStory: Boolean, onElementClick: (ElementModel) -> Unit) {
        if(!isStory) {
            val imageURL = item.thumbnail?.path + "." + item.thumbnail?.extension
            val picasso = Picasso.Builder(context).build()
            picasso.load(imageURL)
                .error(R.drawable.ic_element_placeholder)
                .into(binding.ivElement, object : Callback {
                    override fun onSuccess() {
                        binding.progressBarElementImage.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        binding.progressBarElementImage.visibility = View.GONE
                        e?.message?.also {
                            Log.d("Exception", it)
                        }
                    }

                })
            binding.ivElement.setOnClickListener {
                onElementClick(item)
            }
        } else {
            binding.ivInfoStory.visibility = View.VISIBLE
            binding.ivElement.visibility = View.GONE
            binding.ivInfo.visibility = View.GONE
            binding.progressBarElementImage.visibility = View.GONE
            binding.ivInfoStory.setOnClickListener {
                onElementClick(item)
            }
            binding.tvTitle.setOnClickListener {
                onElementClick(item)
            }
        }

        binding.tvTitle.text = item.title


    }
}