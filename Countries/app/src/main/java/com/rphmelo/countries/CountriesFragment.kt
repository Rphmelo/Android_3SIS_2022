package com.rphmelo.countries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.rphmelo.countries.database.AppDatabase
import com.rphmelo.countries.database.CountryInfo
import com.rphmelo.countries.databinding.FragmentCountriesBinding

class CountriesFragment : Fragment() {

    private var binding: FragmentCountriesBinding? = null
    private val countryAdapter by lazy {
        CountryItemAdapter(
            onDeleteListener = ::openConfirmationDeleteDialog,
            onUpdateListener = ::updateCountry
        )
    }
    private val appDb by lazy {
        view?.context?.let {
            AppDatabase.getDatabase(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountriesBinding.inflate(inflater, container, false)
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

    private fun setupViews(){
        binding?.recyclerViewCountries?.apply {
            setHasFixedSize(true)
            adapter = countryAdapter
        }
        binding?.buttonAddCountry?.setOnClickListener { view ->
            goToRegisterCountry()
        }

        getDataFromDatabase()
    }

    private fun goToRegisterCountry(countryInfo: CountryInfo? = null) {
        findNavController().navigate(R.id.action_to_RegisterCountryFragment, RegisterCountryFragment.buildBundle(countryInfo))
    }

    private fun deleteCountry(countryInfo: CountryInfo) {
        appDb?.countryInfoDao()?.delete(countryInfo)
        binding?.recyclerViewCountries?.let {
            SnackBarUtil.showSnackBar(it, getString(R.string.register_country_success_deleted_message, countryInfo.name))
        }
        getDataFromDatabase()
    }

    private fun updateCountry(countryInfo: CountryInfo) {
        goToRegisterCountry(countryInfo)
    }

    private fun getDataFromDatabase() {
        appDb?.countryInfoDao()?.getAll()?.let {
            countryAdapter.setData(it)
        }
    }

    private fun openConfirmationDeleteDialog(countryInfo: CountryInfo) {
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(resources.getString(R.string.delete_dialog_title))
                .setMessage(resources.getString(R.string.delete_dialog_message, countryInfo.name))
                .setNeutralButton(resources.getString(R.string.delete_cancel_label)) { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton(resources.getString(R.string.delete_continue_label)) { dialog, _ ->
                    deleteCountry(countryInfo)
                    dialog.dismiss()
                }
                .show()
        }
    }
}