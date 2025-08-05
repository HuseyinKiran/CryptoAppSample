package com.huseyinkiran.cryptoappsample.presentation.coinList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huseyinkiran.cryptoappsample.common.Resource
import com.huseyinkiran.cryptoappsample.domain.use_case.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
): ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state: MutableStateFlow<CoinListState> = _state


    fun getCoins() = viewModelScope.launch {

        if (_state.value.coins.isNotEmpty()) return@launch

        getCoinsUseCase().collectLatest { result ->
            when(result) {
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = CoinListState(error = result.message ?: "An unexpected error occured")
                }

                is Resource.Success -> {
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                }

            }
        }
    }

}