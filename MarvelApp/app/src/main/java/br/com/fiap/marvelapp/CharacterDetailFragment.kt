package br.com.fiap.marvelapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import br.com.fiap.marvelapp.data.Api
import br.com.fiap.marvelapp.data.MarvelCharacterDataResultModel
import br.com.fiap.marvelapp.databinding.FragmentCharacterDetailBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class CharacterDetailFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailBinding
    private val character: MarvelCharacterDataResultModel? by lazy {
        arguments?.getParcelable(DETAIL_CHARACTER_KEY) as? MarvelCharacterDataResultModel
    }
    private val comicsAdapter by lazy {
        ComicListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        makeWebServiceCall()
        binding.characterName.text = character?.name
    }

    private fun setupRecyclerView() {
        binding.recyclerViewComics.setHasFixedSize(true)
        binding.recyclerViewComics.adapter = comicsAdapter
    }

    private fun makeWebServiceCall() {
        lifecycleScope.launch {
            character?.id?.let { characterId ->
                val result = Api.listComics(characterId)
                if(result.isSuccessful) {
                    result.body()?.data?.results?.let {
                        comicsAdapter.setData(it)
                    }
                } else {
                    Snackbar.make(
                        binding.recyclerViewComics,
                        "Ocorreu um erro",
                        Snackbar.LENGTH_SHORT
                    )
                }
            }
        }
    }

    companion object {
        private const val DETAIL_CHARACTER_KEY = "DETAIL_CHARACTER_KEY"

        fun buildBundle(characterDataResultModel: MarvelCharacterDataResultModel): Bundle {
            return bundleOf(DETAIL_CHARACTER_KEY to characterDataResultModel)
        }
    }
}