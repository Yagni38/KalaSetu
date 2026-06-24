package com.company.kalasetu.model

data class Review(
    var userEmail: String = "",
    var rating: Float = 0f,
    var comment: String = ""
)