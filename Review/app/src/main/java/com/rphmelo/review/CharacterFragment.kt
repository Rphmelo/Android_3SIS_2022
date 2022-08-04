package com.rphmelo.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.rphmelo.review.databinding.FragmentCharacterBinding

class CharacterFragment : Fragment() {

    private lateinit var binding: FragmentCharacterBinding
    private val characterAdapter by lazy {
        CharacterItemAdapter(Database.characterList)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_character, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        with(binding.recyclerViewCharacters) {
            setHasFixedSize(true)
            adapter = characterAdapter
        }
    }
}