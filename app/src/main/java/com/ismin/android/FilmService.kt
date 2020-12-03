package com.ismin.android

import android.icu.text.CaseMap
import retrofit2.Call
import retrofit2.http.*;

interface FilmService {

    @GET("books")
    fun getAllFilms(): Call<ArrayList<Film>>

    @POST("books")
    fun createFilm(@Body() film: Film): Call<Film>

    @DELETE("/books/{title}")
    fun deleteFilm(@Path("title") title:String):Call<Film>
}