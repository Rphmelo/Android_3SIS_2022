package com.rphmelo.countries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rphmelo.countries.database.AppDatabase
import com.rphmelo.countries.database.CountryInfo
import com.rphmelo.countries.databinding.FragmentRegisterCountryBinding

class RegisterCountryFragment : Fragment() {

    private var binding: FragmentRegisterCountryBinding? = null
    private val appDb by lazy {
        view?.context?.let {
            AppDatabase.getDatabase(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterCountryBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding?.buttonBackToCountries?.setOnClickListener {
            findNavController().navigateUp()
        }

        binding?.registerCountryButton?.setOnClickListener {
            insertData()
        }
    }

    private fun insertData() {
        binding?.run {
            val countryInfo = CountryInfo(
                name = textInputEditTextCountryName.text.toString(),
                language = textInputEditTextCountryLanguage.text.toString(),
                currency = textInputEditTextCountryCurrency.text.toString(),
                location = textInputEditTextCountryLocation.text.toString(),
                capital = textInputEditTextCountryCapital.text.toString(),
            )
            appDb?.countryInfoDao()?.insert(countryInfo)
            clearForm()
        }
    }

    private fun clearForm() {
        binding?.run {
            textInputEditTextCountryName.text?.clear()
            textInputEditTextCountryLanguage.text?.clear()
            textInputEditTextCountryCurrency.text?.clear()
            textInputEditTextCountryLocation.text?.clear()
            textInputEditTextCountryCapital.text?.clear()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}