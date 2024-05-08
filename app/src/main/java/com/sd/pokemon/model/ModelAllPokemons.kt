package com.sd.pokemon.model

import com.sd.pokemon.dto.DataList

data class ModelAllPokemons(
    val loading: Boolean = false,
    val listDataPokemon: DataList = DataList(),
    val error: Boolean = false
)
