package com.rphmelo.countries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rphmelo.countries.database.AppDatabase
import com.rphmelo.countries.databinding.FragmentCountriesBinding

class CountriesFragment : Fragment() {

    private var binding: FragmentCountriesBinding? = null
    private val countryAdapter by lazy {
        CountryItemAdapter()
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
            findNavController().navigate(R.id.action_to_RegisterCountryFragment)
        }

        getDataFromDatabase()
    }

    private fun getDataFromDatabase() {
        appDb?.countryDao()?.getAll()?.let {
            countryAdapter.setData(it)
        }
    }
}