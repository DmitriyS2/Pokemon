package com.sd.pokemon.repository

import com.sd.pokemon.api.ApiService
import com.sd.pokemon.dto.DataCurrentPokemon
import com.sd.pokemon.dto.DataList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : Repository {

    override suspend fun getAllPokemons(offset: Int, limit: Int): DataList {
        try {
            val response = apiService.getAllPokemons(offset, limit)
            if (!response.isSuccessful) {
                return DataList()
            }
            val body = response.body()
            return body ?: DataList()
        } catch (e: Exception) {
            e.printStackTrace()
            return DataList()
        }
    }

    override suspend fun loadDataCurrentPokemon(id: Int): DataCurrentPokemon {
        try {
            val response = apiService.getPokemonById(id)
            if (!response.isSuccessful) {
                return DataCurrentPokemon()
            }
            val body = response.body()
            return body ?: DataCurrentPokemon()
        } catch (e: Exception) {
            e.printStackTrace()
            return DataCurrentPokemon()
        }
    }
}