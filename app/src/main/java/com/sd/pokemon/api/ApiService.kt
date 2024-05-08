package com.sd.pokemon.api

import com.sd.pokemon.dto.DataList
import com.sd.pokemon.dto.DataCurrentPokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    //дать всех покемонов
    @GET("pokemon")
    suspend fun getAllPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<DataList>

    //дать покемона по Id
    @GET("pokemon/{id}/")
    suspend fun getPokemonById(
        @Path("id") id: Int
    ): Response<DataCurrentPokemon>

}