package com.huseyinkiran.cryptoappsample.presentation.coinDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huseyinkiran.cryptoappsample.common.Constants
import com.huseyinkiran.cryptoappsample.common.Resource
import com.huseyinkiran.cryptoappsample.domain.use_case.GetCoinDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinDetailUseCase: GetCoinDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(CoinState())
    val state: MutableStateFlow<CoinState> = _state

    private var hasLoadedOnce = false

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String) = viewModelScope.launch {

        if (hasLoadedOnce) return@launch

        getCoinDetailUseCase(coinId).collectLatest { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CoinState(coin = result.data)
                    hasLoadedOnce = true
                }

                is Resource.Error -> {
                    _state.value =
                        CoinState(error = result.message ?: "An unexpected error occured")
                }

                is Resource.Loading -> {
                    _state.value = CoinState(isLoading = true)
                }
            }
        }
    }
}