package com.sd.pokemon.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.sd.pokemon.R
import com.sd.pokemon.databinding.FragmentCurrentBinding
import com.sd.pokemon.util.StringArg
import com.sd.pokemon.util.animTouch
import com.sd.pokemon.util.loadImage
import com.sd.pokemon.viewmodel.MainViewModel

class CurrentFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCurrentBinding.inflate(inflater, container, false)

        val idCurrent = arguments?.textArgument?.toInt() ?: 0

        viewModel.dataCurrentPokemon.observe(viewLifecycleOwner) { model ->
            binding.groupErrorCurrent.isVisible = model.error
            binding.progressCurrent.isVisible = model.loading
            binding.groupCurrent.isVisible = !(model.loading || model.error)

            binding.apply {
                currentName.text = model.currentPokemon.name
                currentId.text = getString(R.string.id_str, model.currentPokemon.id.toString())
                currentWeight.text =
                    getString(R.string.weight_str, model.currentPokemon.weight.toString())
                currentExp.text =
                    getString(R.string.exp_str, model.currentPokemon.base_experience.toString())
                currentDefault.text =
                    getString(R.string.default_str, model.currentPokemon.is_default.toString())
                currentHeight.text =
                    getString(R.string.height_str, model.currentPokemon.height.toString())

                imageCurrent1.loadImage(model.currentPokemon.sprites.back_default)
                imageCurrent2.loadImage(model.currentPokemon.sprites.back_shiny)
                imageCurrent3.loadImage(model.currentPokemon.sprites.front_default)
                imageCurrent4.loadImage(model.currentPokemon.sprites.front_shiny)
            }
        }

        binding.buttonBack.setOnClickListener {
            it.animTouch()
            findNavController()
                .navigate(R.id.mainFragment)
        }

        binding.buttonRetryCurrent.setOnClickListener {
            it.animTouch()
            viewModel.loadDataCurrentPokemon(idCurrent)
        }

        return binding.root
    }

    companion object {
        var Bundle.textArgument: String? by StringArg
    }
}