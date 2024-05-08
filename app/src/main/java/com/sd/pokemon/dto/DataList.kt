package com.sd.pokemon.dto

data class DataList(
    val count: Int = 0,
    val next: String? = null,
    val previous: String? = null,
    val results: List<DataPokemon> = emptyList()
)