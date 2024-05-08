package com.sd.pokemon.repository

import com.sd.pokemon.dto.DataCurrentPokemon
import com.sd.pokemon.dto.DataList

interface Repository {

    suspend fun getAllPokemons(offset: Int, limit: Int): DataList

    suspend fun loadDataCurrentPokemon(id: Int): DataCurrentPokemon

}