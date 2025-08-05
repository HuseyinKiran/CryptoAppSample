package com.huseyinkiran.cryptoappsample.presentation.coinDetail

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexboxLayoutManager
import com.huseyinkiran.cryptoappsample.R
import com.huseyinkiran.cryptoappsample.common.viewBinding
import com.huseyinkiran.cryptoappsample.databinding.FragmentCoinDetailBinding
import com.huseyinkiran.cryptoappsample.presentation.adapter.tag.TagAdapter
import com.huseyinkiran.cryptoappsample.presentation.adapter.team.TeamAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoinDetailFragment : Fragment(R.layout.fragment_coin_detail) {

    private val binding by viewBinding(FragmentCoinDetailBinding::bind)
    private val viewModel: CoinDetailViewModel by viewModels()

    private val teamAdapter: TeamAdapter by lazy {
        TeamAdapter()
    }

    private val tagAdapter: TagAdapter by lazy {
        TagAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        setupAdapters()
        collectViewModelData()
    }

    private fun setupAdapters() = with(binding) {
        rvTeamMembers.layoutManager = LinearLayoutManager(requireContext())
        rvTeamMembers.adapter = teamAdapter

        rvTags.layoutManager = FlexboxLayoutManager(requireContext())
        rvTags.adapter = tagAdapter
    }

    private fun collectViewModelData() = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->

                progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE

                txtError.visibility = if (state.error.isNotBlank()) View.VISIBLE else View.GONE
                txtError.text = state.error

                state.coin?.let { coin ->

                    Glide
                        .with(this@CoinDetailFragment)
                        .load(coin.logo)
                        .centerCrop()
                        .into(imgCrypto)

                    txtName.text = coin.name
                    txtRank.text = "${coin.rank}."
                    txtDesc.text = coin.description
                    txtIsActive.text = if (coin.isActive == true) "active" else "inactive"

                    txtIsActive.setTextColor(
                        if (coin.isActive == true) Color.GREEN else Color.RED
                    )

                    tagAdapter.submitList(coin.tags)
                    teamAdapter.submitList(coin.team)

                    if (coin.team.isEmpty()) teamMembersLayout.isGone = true
                }
            }
        }
    }

}