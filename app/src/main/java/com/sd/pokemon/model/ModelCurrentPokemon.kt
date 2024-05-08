package com.sd.pokemon.model

import com.sd.pokemon.dto.DataCurrentPokemon

data class ModelCurrentPokemon(
    val loading: Boolean = false,
    val currentPokemon: DataCurrentPokemon = DataCurrentPokemon(),
    val error: Boolean = false
)