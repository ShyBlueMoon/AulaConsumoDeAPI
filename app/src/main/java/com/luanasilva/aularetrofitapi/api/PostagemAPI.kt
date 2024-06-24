package com.luanasilva.aularetrofitapi.api

import com.luanasilva.aularetrofitapi.model.Comentario
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostagemAPI {


    //Recuperando postagem de id 1

    /*@GET("posts/{id}/comments")
    suspend fun recuperarComentariosParaPostagem(
        @Path ("id") id:Int
    ): Response<List<Comentario>>
*/
    @GET("comments") //comments?postId=1
    suspend fun recuperarComentariosParaPostagemQuery(
        @Query ("postId") id:Int
    ): Response<List<Comentario>>



}