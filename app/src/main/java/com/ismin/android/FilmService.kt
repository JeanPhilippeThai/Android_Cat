package com.ismin.android

import android.icu.text.CaseMap
import retrofit2.Call
import retrofit2.http.*;

interface FilmService {

    @GET("films")
    fun getAllFilms(): Call<ArrayList<Film>>

    @POST("films")
    fun createFilm(@Body() film: Film): Call<Film>

    @DELETE("/films/{title}")//gu
    fun deleteFilm(@Path("title") title:String):Call<Film>//gu
}