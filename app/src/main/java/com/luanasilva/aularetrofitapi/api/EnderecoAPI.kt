package com.luanasilva.aularetrofitapi.api

import com.luanasilva.aularetrofitapi.model.Endereco
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoAPI {


    //BASE URL  https://viacep.com.br/
    //GET recebe a rota
    //GET, POST, PUT, PATCH, DELETE
    @GET("ws/{cep}/json/")
    suspend fun recuperarEndereco(
        @Path("cep") cep: String
    ) : Response<Endereco>
}