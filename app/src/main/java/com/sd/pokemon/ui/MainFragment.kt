package com.sd.pokemon.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sd.pokemon.R
import com.sd.pokemon.adapter.Listener
import com.sd.pokemon.adapter.PokemonAdapter
import com.sd.pokemon.databinding.FragmentMainBinding
import com.sd.pokemon.dto.DataPokemon
import com.sd.pokemon.ui.CurrentFragment.Companion.textArgument
import com.sd.pokemon.util.animTouch
import com.sd.pokemon.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)

        val adapter = PokemonAdapter(object : Listener {
            override fun goToPokemon(dataPokemon: DataPokemon) {
                viewModel.loadDataCurrentPokemon(dataPokemon.id)

                findNavController()
                    .navigate(R.id.currentFragment,
                        Bundle().apply {
                            textArgument = dataPokemon.id.toString()
                        })
            }
        })

        binding.rwPokemons.layoutManager = LinearLayoutManager(activity)
        binding.rwPokemons.adapter = adapter

        viewModel.dataPokemons.observe(viewLifecycleOwner) { model ->
            binding.groupError.isVisible = model.error
            binding.progress.isVisible = model.loading
            adapter.submitList(model.listDataPokemon.results)
        }

        binding.buttonRetry.setOnClickListener {
            it.animTouch()
            viewModel.loadDataList()
        }

        return binding.root
    }
}