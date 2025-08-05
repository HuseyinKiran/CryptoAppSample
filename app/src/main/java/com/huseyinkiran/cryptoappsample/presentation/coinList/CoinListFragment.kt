package com.huseyinkiran.cryptoappsample.presentation.coinList

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.huseyinkiran.cryptoappsample.R
import com.huseyinkiran.cryptoappsample.common.viewBinding
import com.huseyinkiran.cryptoappsample.databinding.FragmentCoinListBinding
import com.huseyinkiran.cryptoappsample.presentation.adapter.coin.CoinAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoinListFragment : Fragment(R.layout.fragment_coin_list) {

    private val viewModel: CoinListViewModel by activityViewModels()
    private val binding by viewBinding(FragmentCoinListBinding::bind)

    private val adapter: CoinAdapter by lazy {
        CoinAdapter(callback = object : CoinAdapter.CoinListener {

            override fun onCoinClick(coinId: String) {
                val action = CoinListFragmentDirections.actionCoinListFragmentToCoinDetailFragment(coinId)
                findNavController().navigate(action)
            }

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        finishActivity()
        collectViewModel()
        viewModel.getCoins()
    }

    private fun setupAdapter() = with(binding) {
        rvCoins.layoutManager = LinearLayoutManager(requireContext())
        rvCoins.adapter = adapter
    }

    private fun collectViewModel() = with(binding) {

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                Log.d("CoinListFragment", "State updated: ${state.coins.size} coins")

                adapter.submitList(state.coins)

                progressBar.isVisible = state.isLoading
                txtError.isVisible = state.error.isNotBlank()
                txtError.text = state.error
            }
        }

    }

    private fun finishActivity() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }
    }

}