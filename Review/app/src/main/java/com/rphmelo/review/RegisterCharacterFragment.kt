package com.rphmelo.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.rphmelo.review.databinding.FragmentRegisterCharacterBinding
import com.rphmelo.review.model.CharacterInfo

class RegisterCharacterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterCharacterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_character, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun clearFields() {
        binding.textInputEditTextCharacterName.text?.clear()
        binding.textInputEditTextCharacterDescription.text?.clear()
    }

    private fun setupViews() {
        with(binding) {
            registerButton.setOnClickListener {
                val characterInfo = CharacterInfo(
                    name = textInputEditTextCharacterName.text.toString(),
                    description = textInputEditTextCharacterDescription.text.toString()
                )

                Database.characterList.add(characterInfo)
                clearFields()
            }
        }
    }
}