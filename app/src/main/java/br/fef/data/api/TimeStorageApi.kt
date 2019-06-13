package br.fef.data.api

import br.fef.data.api.dto.*
import br.fef.data.persistence.entity.User
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TimeStorageApi : TimeStorageApiDef {

    private val service: TimeStorageApiDef

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://timestorage.ddns.net:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(TimeStorageApiDef::class.java)
    }

    override fun doLogin(login: Login): Call<User> {
        return service.doLogin(login)
    }

    override fun doRegister(register: Register): Call<Void> {
        return service.doRegister(register)
    }

    override fun getAllAutores(): Call<List<Autor>> {
        return service.getAllAutores()
    }

    override fun getAllCategorias(): Call<List<Categoria>> {
        return service.getAllCategorias()
    }

    override fun getDashboard(): Call<List<Dashboard>> {
        return service.getDashboard()
    }

    override fun getDocumentoDetail(cod: Int?): Call<Documento> {
        return service.getDocumentoDetail(cod)
    }

    override fun getAllDocumentos(): Call<List<DocumentoSimplificado>> {
        return service.getAllDocumentos()
    }

    override fun getAllEditoras(): Call<List<Editora>> {
        return service.getAllEditoras()
    }

    override fun getAllGeneros(): Call<List<Genero>> {
        return service.getAllGeneros()
    }

    override fun sendAutor(autor: Autor): Call<Void> {
        return service.sendAutor(autor)
    }

    override fun sendCategoria(categoria: Categoria): Call<Void> {
        return service.sendCategoria(categoria)
    }

    override fun sendEditora(editora: Editora): Call<Void> {
        return service.sendEditora(editora)
    }

    override fun sendGenero(genero: Genero): Call<Void> {
        return service.sendGenero(genero)
    }

    override fun alterAutor(autor: Autor): Call<Void> {
        return service.alterAutor(autor)
    }

    override fun alterCategoria(categoria: Categoria): Call<Void> {
        return service.alterCategoria(categoria)
    }

    override fun alterEditora(editora: Editora): Call<Void> {
        return service.sendEditora(editora)
    }

    override fun alterGenero(genero: Genero): Call<Void> {
        return service.sendGenero(genero)
    }

}