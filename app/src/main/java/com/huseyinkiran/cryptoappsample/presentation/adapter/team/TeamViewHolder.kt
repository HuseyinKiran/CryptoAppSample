package com.huseyinkiran.cryptoappsample.presentation.adapter.team

import androidx.recyclerview.widget.RecyclerView
import com.huseyinkiran.cryptoappsample.databinding.ItemTeamMembersBinding
import com.huseyinkiran.cryptoappsample.domain.model.TeamMember

class TeamViewHolder(private val binding: ItemTeamMembersBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(
        teamMember: TeamMember
    ) = with(binding) {

        txtMemberName.text = teamMember.name
        txtMemberTitle.text = teamMember.position
    }

}