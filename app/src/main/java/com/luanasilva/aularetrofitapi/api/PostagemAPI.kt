package com.luanasilva.aularetrofitapi.api

import com.luanasilva.aularetrofitapi.model.Postagem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostagemAPI {


    //Recuperando postagem de id 1

    @GET("posts/{id}")
    suspend fun recuperarPostagemUnica(
        @Path ("id") id:Int
    ): Response<Postagem>
}