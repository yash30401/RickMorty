package com.devyash.rickmorty.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devyash.rickmorty.R
import com.devyash.rickmorty.databinding.CharacterItemLayoutBinding
import com.devyash.rickmorty.domain.CharacterSurface

class CharacterAdapter(private val charctersList: List<CharacterSurface>,  private val characterClickListner: CharacterClickListner) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = CharacterItemLayoutBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val viewHolder = CharacterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.character_item_layout, parent, false)
        )

        viewHolder.binding.characterContainer.setOnClickListener {
            characterClickListner.onClick(charctersList[viewHolder.adapterPosition])
        }

        return viewHolder
    }

    override fun getItemCount(): Int {
        return charctersList.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val currentCharacter = charctersList[position]

        Glide.with(holder.itemView.context).load(currentCharacter.image.toUri())
            .into(holder.binding.ivCharacterImage)
    }

}

interface CharacterClickListner{
    fun onClick(characterSurface: CharacterSurface)
}