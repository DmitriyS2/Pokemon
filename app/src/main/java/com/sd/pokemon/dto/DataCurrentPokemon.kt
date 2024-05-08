package com.sd.pokemon.dto

data class DataCurrentPokemon(
    val id: Int = 0,
    val name: String = "",
    val weight: Int = 0,
    val height: Int = 0,
    val base_experience: Int = 0,
    val is_default: Boolean = true,
    val sprites: Sprites = Sprites()
)

data class Sprites(
    val back_default: String = "",
    val back_shiny: String = "",
    val front_default: String = "",
    val front_shiny: String = ""
)