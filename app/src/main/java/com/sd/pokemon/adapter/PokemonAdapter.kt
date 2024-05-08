package com.sd.pokemon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sd.pokemon.R
import com.sd.pokemon.databinding.ItemPokemonBinding
import com.sd.pokemon.dto.DataPokemon

interface Listener {
    fun goToPokemon(dataPokemon: DataPokemon)
}

class PokemonAdapter(private val listener: Listener) :
    ListAdapter<DataPokemon, PokemonAdapter.PokemonHolder>(PokemonDiffCallback()) {

    class PokemonHolder(item: View, private val listener: Listener) :
        RecyclerView.ViewHolder(item) {

        private val binding = ItemPokemonBinding.bind(item)

        fun bind(dataPokemon: DataPokemon) = with(binding) {

            name.text = dataPokemon.name

            cardViewItemPokemon.setOnClickListener {
                listener.goToPokemon(dataPokemon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)
        return PokemonHolder(view, listener)
    }

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        val pokemon = getItem(position)
        holder.bind(pokemon)
    }
}

class PokemonDiffCallback : DiffUtil.ItemCallback<DataPokemon>() {
    override fun areItemsTheSame(oldItem: DataPokemon, newItem: DataPokemon): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: DataPokemon, newItem: DataPokemon): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: DataPokemon, newItem: DataPokemon): Any =
        Payload()
}

data class Payload(
    val id: Int? = null
)
