package com.alkowsartech.pexelphotos.data.remote.model

data class Pexel(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val photos: List<Photo>,
    val total_results: Int
)