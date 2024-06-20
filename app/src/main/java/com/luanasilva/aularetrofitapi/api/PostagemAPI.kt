package com.luanasilva.aularetrofitapi.api

import com.luanasilva.aularetrofitapi.model.Comentario
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostagemAPI {


    //Recuperando postagem de id 1

    @GET("posts/{id}/comments")
    suspend fun recuperarComentariosParaPostagem(
        @Path ("id") id:Int
    ): Response<List<Comentario>>
}