package com.rphmelo.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rphmelo.countries.database.CountryInfo
import com.rphmelo.countries.databinding.ViewCountryItemBinding

class CountryItemAdapter(
    private val onDeleteListener: (CountryInfo) -> Unit = {},
    private val onUpdateListener: (CountryInfo) -> Unit = {},
) : RecyclerView.Adapter<CountryItemAdapter.CharacterItemViewHolder>() {

    private var countryList: MutableList<CountryInfo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItemViewHolder {
        val binding = ViewCountryItemBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.view_country_item, parent, false))
        return CharacterItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterItemViewHolder, position: Int) {
        holder.bindView(countryList[position], position)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun setData(list: List<CountryInfo>) {
        with(countryList) {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    inner class CharacterItemViewHolder(
        private val view: ViewCountryItemBinding
    ) : RecyclerView.ViewHolder(view.root) {

        fun bindView(countryInfo: CountryInfo, itemPosition: Int) {
            view.countryNameValue.text = countryInfo.name
            view.countryCapitalValue.text = countryInfo.capital
            view.countryLanguageValue.text = countryInfo.language
            view.countryLocationValue.text = countryInfo.location
            view.countryCurrencyValue.text = countryInfo.currency

            view.iconDelete.setOnClickListener {
                onDeleteListener.invoke(countryInfo)
            }

            view.iconUpdate.setOnClickListener {
                onUpdateListener.invoke(countryInfo)
            }
        }
    }
}