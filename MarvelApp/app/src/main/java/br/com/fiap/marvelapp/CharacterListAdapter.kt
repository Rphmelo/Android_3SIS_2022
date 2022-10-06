package br.com.fiap.marvelapp

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.marvelapp.databinding.ViewCharacterItemBinding

class CharacterListAdapter : RecyclerView.Adapter<CharacterListAdapter.CharacterItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItemViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CharacterItemViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class CharacterItemViewHolder(
        private val view: ViewCharacterItemBinding
    ) : RecyclerView.ViewHolder(view.root)
}