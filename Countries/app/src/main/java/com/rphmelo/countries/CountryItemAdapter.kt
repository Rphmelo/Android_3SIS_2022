package com.rphmelo.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rphmelo.countries.database.CountryInfo
import com.rphmelo.countries.databinding.ViewCountryItemBinding

class CountryItemAdapter : RecyclerView.Adapter<CountryItemAdapter.CharacterItemViewHolder>() {

    private var countryList: MutableList<CountryInfo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItemViewHolder {
        val binding = ViewCountryItemBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.view_country_item, parent, false))
        return CharacterItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterItemViewHolder, position: Int) {
        holder.bindView(countryList[position])
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun setData(list: List<CountryInfo>) {
        countryList.clear()
        countryList.addAll(list)
        notifyDataSetChanged()
    }

    inner class CharacterItemViewHolder(
        private val view: ViewCountryItemBinding
    ) : RecyclerView.ViewHolder(view.root) {

        fun bindView(countryInfo: CountryInfo) {
            view.countryNameValue.text = countryInfo.name
            view.countryCapitalValue.text = countryInfo.capital
            view.countryLanguageValue.text = countryInfo.language
            view.countryLocationValue.text = countryInfo.location
            view.countryCurrencyValue.text = countryInfo.currency
        }
    }
}