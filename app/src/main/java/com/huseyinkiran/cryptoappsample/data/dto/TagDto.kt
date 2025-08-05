package com.huseyinkiran.cryptoappsample.data.dto

import com.google.gson.annotations.SerializedName
import com.huseyinkiran.cryptoappsample.domain.model.Tag

data class TagDto(
    @SerializedName("coin_counter")
    val coinCounter: Int,
    @SerializedName("ico_counter")
    val icoCounter: Int,
    val id: String,
    val name: String
)

fun TagDto.toTag(): Tag {
    return Tag(
        name = name
    )
}