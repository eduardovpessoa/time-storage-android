package br.fef.data.api

import br.fef.data.api.dto.*
import br.fef.data.persistence.entity.User
import okhttp3.Response
import retrofit2.Call
import retrofit2.http.*

interface TimeStorageApiDef {

    @POST("login")
    fun doLogin(@Body login: Login): Call<User>

    @POST("cadastrar")
    fun doRegister(@Body register: Register): Call<Void>

    @GET("autor")
    fun getAllAutores(): Call<List<Autor>>

    @GET("categoria")
    fun getAllCategorias(): Call<List<Categoria>>

    @GET("editora")
    fun getAllEditoras(): Call<List<Editora>>

    @GET("genero")
    fun getAllGeneros(): Call<List<Genero>>

    @POST("envautor")
    fun sendAutor(@Body autor: Autor): Call<Void>

    @POST("envcategoria")
    fun sendCategoria(@Body categoria: Categoria): Call<Void>

    @POST("enveditora")
    fun sendEditora(@Body editora: Editora): Call<Void>

    @POST("envgenero")
    fun sendGenero(@Body genero: Genero): Call<Void>

    @PUT("updautor")
    fun alterAutor(@Body autor: Autor): Call<Void>

    @POST("updcategoria")
    fun alterCategoria(@Body categoria: Categoria): Call<Void>

    @POST("updeditora")
    fun alterEditora(@Body editora: Editora): Call<Void>

    @POST("updgenero")
    fun alterGenero(@Body genero: Genero): Call<Void>

}