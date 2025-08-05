package com.huseyinkiran.cryptoappsample.data.dto

import com.huseyinkiran.cryptoappsample.domain.model.TeamMember

data class TeamMemberDto(
    val id: String,
    val name: String,
    val position: String
)

fun TeamMemberDto.toTeamMember(): TeamMember {
    return TeamMember(
        name = name,
        position = position
    )
}