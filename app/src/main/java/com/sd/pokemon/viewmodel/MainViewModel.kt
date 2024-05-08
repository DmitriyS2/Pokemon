package com.sd.pokemon.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sd.pokemon.dto.DataCurrentPokemon
import com.sd.pokemon.dto.DataList
import com.sd.pokemon.dto.DataPokemon
import com.sd.pokemon.model.ModelAllPokemons
import com.sd.pokemon.model.ModelCurrentPokemon
import com.sd.pokemon.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    //   private val initUrl: String = "https://pokeapi.co/api/v2/pokemon/"

    private val start = 0
    private val limit = 1302

    private val _dataPokemons = MutableLiveData<ModelAllPokemons>()
    val dataPokemons: LiveData<ModelAllPokemons>
        get() = _dataPokemons

    private val _dataCurrentPokemon = MutableLiveData<ModelCurrentPokemon>()
    val dataCurrentPokemon: LiveData<ModelCurrentPokemon>
        get() = _dataCurrentPokemon

    init {
        loadDataList()
    }

    fun loadDataList() {
        viewModelScope.launch {
            try {
                _dataPokemons.value = ModelAllPokemons(loading = true)

                val list = repository.getAllPokemons(start, limit)
                val temp = DataList(
                    count = list.count,
                    next = list.next,
                    previous = list.previous,
                    results = list.results.map {
                        DataPokemon(
                            id = try {
                                val str = it.url.split('/')
                                val index = str[str.size - 2].toInt()
                                index
                            } catch (e: Exception) {
                                0
                            },
                            name = it.name,
                            url = it.url
                        )
                    }
                )

                _dataPokemons.value = ModelAllPokemons(
                    listDataPokemon = temp
                )
                if (_dataPokemons.value?.listDataPokemon == DataList()) {
                    _dataPokemons.value = ModelAllPokemons(error = true)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _dataPokemons.value = ModelAllPokemons(error = true)
            }
        }
    }

    fun loadDataCurrentPokemon(id: Int) {
        viewModelScope.launch {
            try {
                _dataCurrentPokemon.value = ModelCurrentPokemon(loading = true)

                _dataCurrentPokemon.value =
                    ModelCurrentPokemon(currentPokemon = repository.loadDataCurrentPokemon(id))

                if (_dataCurrentPokemon.value?.currentPokemon == DataCurrentPokemon()) {
                    _dataCurrentPokemon.value = ModelCurrentPokemon(error = true)
                }

            } catch (e: Exception) {
                _dataCurrentPokemon.value = ModelCurrentPokemon(error = true)
            }
        }
    }
}