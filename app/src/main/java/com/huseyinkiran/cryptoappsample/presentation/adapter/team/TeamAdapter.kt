package com.huseyinkiran.cryptoappsample.presentation.adapter.team

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.huseyinkiran.cryptoappsample.databinding.ItemTeamMembersBinding
import com.huseyinkiran.cryptoappsample.domain.model.TeamMember

class TeamAdapter: RecyclerView.Adapter<TeamViewHolder>() {

    private var teamMembers: List<TeamMember> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding = ItemTeamMembersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamViewHolder(binding)
    }

    override fun getItemCount(): Int = teamMembers.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team = teamMembers[position]
        holder.bind(team)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: List<TeamMember>) {
        teamMembers = newList
        notifyDataSetChanged()
    }
}