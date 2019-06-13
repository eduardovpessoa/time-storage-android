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
    fun getAllAutores() : Call<List<Autor>>

    @GET("categoria")
    fun getAllCategorias() : Call<List<Categoria>>

    @GET("editora")
    fun getAllEditoras() : Call<List<Editora>>

    @GET("genero")
    fun getAllGeneros() : Call<List<Genero>>

}