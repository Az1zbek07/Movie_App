package com.example.movieapp.model

import java.io.Serializable

data class ItemData(
    val id: Int? = null,
    val name: String? = null,
    val authors: String? = null,
    val message: String? = null,
    val data: String? = null,
    val isEdit: String? = null,
): Serializable
