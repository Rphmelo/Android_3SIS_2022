package com.rphmelo.review
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rphmelo.review.databinding.ViewCharacterItemBinding
import com.rphmelo.review.model.CharacterInfo

class CharacterItemAdapter(
    private val characterList: MutableList<CharacterInfo>
) : RecyclerView.Adapter<CharacterItemAdapter.CharacterItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItemViewHolder {
        val viewRoot = LayoutInflater.from(parent.context).inflate(R.layout.view_character_item, parent, false)
        val binding = ViewCharacterItemBinding.bind(viewRoot)
        return CharacterItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterItemViewHolder, position: Int) {
        holder.bindView(characterList[position])
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    inner class CharacterItemViewHolder(
        private val view: ViewCharacterItemBinding
    ) : RecyclerView.ViewHolder(view.root) {

        fun bindView(characterInfo: CharacterInfo) {
            view.characterName.text = characterInfo.name
            view.characterDescription.text = characterInfo.description
        }
    }
}