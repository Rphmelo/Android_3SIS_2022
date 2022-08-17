package com.rphmelo.countries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
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
    private val countryInfoArgument by lazy {
        arguments?.getParcelable(COUNTRY_INFO_BUNDLE_KEY) as? CountryInfo
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

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setupViews() {
        binding?.buttonBackToCountries?.setOnClickListener {
            findNavController().navigateUp()
        }

        binding?.registerUpdateCountryButton?.run {
            text = if(countryInfoArgument == null) {
                getString(R.string.register_country_button_label)
            } else {
                getString(R.string.update_country_button_label)
            }

            setOnClickListener {
                insertUpdateData()
            }
        }

        countryInfoArgument?.let { countryInfoArgument ->
            binding?.run {
                textInputEditTextCountryName.setText(countryInfoArgument.name)
                textInputEditTextCountryLanguage.setText(countryInfoArgument.language)
                textInputEditTextCountryCurrency.setText(countryInfoArgument.currency)
                textInputEditTextCountryLocation.setText(countryInfoArgument.location)
                textInputEditTextCountryCapital.setText(countryInfoArgument.capital)
            }
        }
    }

    private fun insertUpdateData() {
        binding?.run {
            val countryInfo = CountryInfo(
                name = textInputEditTextCountryName.text.toString(),
                language = textInputEditTextCountryLanguage.text.toString(),
                currency = textInputEditTextCountryCurrency.text.toString(),
                location = textInputEditTextCountryLocation.text.toString(),
                capital = textInputEditTextCountryCapital.text.toString(),
            )

            countryInfoArgument?.let {
                countryInfo.id = it.id
            }

            appDb?.countryInfoDao()?.run {
                val snackBarMessageResId: Int = if(countryInfoArgument == null) {
                    insert(countryInfo)
                    R.string.register_country_success_registered_message
                } else {
                    update(countryInfo)
                    R.string.register_country_success_updated_message
                }
                clearForm()
                showRegisterMessage(getString(snackBarMessageResId, countryInfo.name))
            }
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

    private fun showRegisterMessage(message: String) {
        binding?.buttonBackToCountries?.let {
            SnackBarUtil.showSnackBar(it, message)
        }
    }

    companion object {
        const val COUNTRY_INFO_BUNDLE_KEY = "COUNTRY_INFO_BUNDLE_KEY"

        fun buildBundle(countryInfo: CountryInfo?): Bundle? {
            return countryInfo?.let {
                bundleOf(COUNTRY_INFO_BUNDLE_KEY to it)
            }
        }
    }
}