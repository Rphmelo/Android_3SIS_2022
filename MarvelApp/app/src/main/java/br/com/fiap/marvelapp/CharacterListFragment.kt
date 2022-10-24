package br.com.fiap.marvelapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import br.com.fiap.marvelapp.data.Api
import br.com.fiap.marvelapp.databinding.FragmentCharacterListBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class CharacterListFragment : Fragment() {

    private lateinit var binding: FragmentCharacterListBinding
    private val characterListAdapter by lazy {
        CharacterListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        makeWebServiceCall()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewCharacters.setHasFixedSize(true)
        binding.recyclerViewCharacters.adapter = characterListAdapter
    }

    private fun makeWebServiceCall() {
        lifecycleScope.launch {
            val result = Api.listCharacters()
            if(result.isSuccessful) {
                result.body()?.data?.results?.let {
                    characterListAdapter.setData(it)
                }
            } else {
                Snackbar.make(
                    binding.recyclerViewCharacters,
                    "Ocorreu um erro",
                    Snackbar.LENGTH_SHORT
                )
            }
        }
    }
}