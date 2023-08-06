package com.devyash.rickmorty.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devyash.rickmorty.domain.CharacterSurface
import com.devyash.rickmorty.domain.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val getCharacterUseCase: GetCharacterUseCase) : ViewModel() {

    private val _state = MutableStateFlow(CharactersState())
    val state:StateFlow<CharactersState> = _state

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            _state.update {
                it.copy(
                    characters = getCharacterUseCase.execute(1),
                    isLoading = false
                )
            }
        }
    }

    data class CharactersState(
        val characters: List<CharacterSurface> = emptyList(),
        val isLoading: Boolean = false,
    )
}