package br.fef.data.api

import br.fef.data.api.dto.Login
import br.fef.data.api.dto.Register
import br.fef.data.persistence.entity.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface TimeStorageApiDef {

    @POST("login")
    fun doLogin(@Body login: Login): Call<User>

    @POST("cadastrar")
    fun doRegister(@Body register: Register): Call<Register>
}