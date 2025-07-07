package com.example.clineapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

sealed interface HoroscopeUiState {
    data class Success(val horoscope: HoroscopeDetailDto) : HoroscopeUiState
    object Error : HoroscopeUiState
    object Loading : HoroscopeUiState
}

@HiltViewModel
class HoroscopeViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HoroscopeUiState>(HoroscopeUiState.Loading)
    val uiState: StateFlow<HoroscopeUiState> = _uiState.asStateFlow()

    init {
        fetchHoroscope()
    }

    fun fetchHoroscope() {
        val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
        userRepository.getHoroscope(today)
            .onEach { result ->
                val newState = when (result) {
                    is Result.Success -> HoroscopeUiState.Success(result.data)
                    is Result.Error -> HoroscopeUiState.Error
                    is Result.Loading -> HoroscopeUiState.Loading
                }
                _uiState.value = newState
            }
            .launchIn(viewModelScope)
    }
}
