package com.rphmelo.countries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
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

    private val sharedPreferences by lazy {
        AppDatabase.getSharedPreferences(requireActivity())
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
        binding?.buttonBackToCountries?.setOnClickListener {
            findNavController().navigateUp()
        }
        setupViews()
    }

    fun setupViews() {
        binding?.buttonBackToCountries?.setOnClickListener {
            findNavController().navigateUp()
        }

        binding?.registerCountryButton?.setOnClickListener {
            insertData()
        }
        binding?.registerCountryButton?.visibility =
            if(sharedPreferences.getBoolean("HIDE_BUTTON_REGISTER", false)) {
                View.GONE
            } else {
                View.VISIBLE
            }
    }

    fun insertData() {
        val countryInfo = CountryInfo(
            name = binding?.textInputEditTextCountryName?.text.toString(),
            language = binding?.textInputEditTextCountryLanguage?.text.toString(),
            currency = binding?.textInputEditTextCountryCurrency?.text.toString(),
            location = binding?.textInputEditTextCountryLocation?.text.toString(),
            capital = binding?.textInputEditTextCountryCapital?.text.toString(),
        )
        appDb?.countryDao()?.insert(countryInfo)
        clearForm()
        binding?.buttonBackToCountries?.let {
            SnackBarUtil.showSnackBar(it, "Cadastrado com sucesso!!")
        }
        sharedPreferences.edit().apply {
            putBoolean("HIDE_BUTTON_REGISTER", true)
        }.apply()

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