package com.luanasilva.aularetrofitapi.model

data class Endereco(
    val bairro: String,
    val cep: String,
    val complemento: String,
    val ddd: String,
    val gia: String,
    val ibge: String,
    val localidade: String,
    val logradouro: String,
    val siafi: String,
    val uf: String
)