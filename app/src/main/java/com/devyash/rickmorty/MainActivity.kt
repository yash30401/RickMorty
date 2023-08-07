package com.devyash.rickmorty

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.devyash.rickmorty.databinding.ActivityMainBinding
import com.devyash.rickmorty.domain.CharacterSurface
import com.devyash.rickmorty.presentation.CharactersViewModel
import com.devyash.rickmorty.presentation.adapter.CharacterAdapter
import com.devyash.rickmorty.presentation.adapter.CharacterClickListner
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CharacterClickListner {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<CharactersViewModel>()

    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalScope.launch {
            viewModel.state.collect { it ->
                characterAdapter = CharacterAdapter(it.characters, this@MainActivity)
                withContext(Dispatchers.Main) {
                    binding.recylerView.apply {
                        adapter = characterAdapter
                        layoutManager = GridLayoutManager(this@MainActivity, 2)
                    }
                }
            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(characterSurface: CharacterSurface) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_layout, null)

        val characterImage = dialogView.findViewById<ImageView>(R.id.ivCharacterDialog)
        val characterName = dialogView.findViewById<TextView>(R.id.tvCharacterName)
        val characterOrigin = dialogView.findViewById<TextView>(R.id.tvCharacterOrigin)
        val characterSpecies = dialogView.findViewById<TextView>(R.id.tvCharacterSpecies)
        val characterLocation = dialogView.findViewById<TextView>(R.id.tvCharacterLocation)

        Glide.with(this)
            .load(characterSurface.image)
            .into(characterImage)

        characterName.text = characterSurface.name
        characterOrigin.text = "Origin: ${characterSurface.origin}"
        characterSpecies.text = "Species: ${characterSurface.species}"
        characterLocation.text = "Location: ${characterSurface.location}"

        MaterialAlertDialogBuilder(this)
            .setView(dialogView)
            .setCancelable(true)
            .show()



    }
}