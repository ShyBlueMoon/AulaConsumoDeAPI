package com.luanasilva.aularetrofitapi.model

data class Comentario(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)