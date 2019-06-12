package br.fef.data.api

import br.fef.data.api.dto.Categoria
import br.fef.data.api.dto.Login
import br.fef.data.api.dto.Register
import br.fef.data.persistence.entity.User
import okhttp3.Response
import retrofit2.Call
import retrofit2.http.*

interface TimeStorageApiDef {

    @POST("login")
    fun doLogin(@Body login: Login): Call<User>

    @POST("cadastrar")
    fun doRegister(@Body register: Register): Call<Void>

    @GET("categoria")
    fun getAllCategorias() : Call<List<Categoria>>
}