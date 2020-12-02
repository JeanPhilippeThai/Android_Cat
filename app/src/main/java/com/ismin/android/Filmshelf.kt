package com.ismin.android

import java.io.Serializable

class Filmshelf : Serializable {
    private val storage = HashMap<String, Film>()

    fun addFilm(film: Film) {
        this.storage[film.title] = film
    }

    fun getFilm(title: String): Film? {
        return this.storage[title]
    }

    fun getAllFilms(): List<Film> {
        return ArrayList(this.storage.values).sortedBy { film -> film.title }
    }

    fun getFilmsOf(author: String): List<Film> {
        val filteredStorage = this.storage.filter { it.value.author == author }
        return ArrayList(filteredStorage.values).sortedBy { film -> film.title }
    }

    fun getTotalNumberOfFilms(): Int {
        return this.storage.size
    }
    fun deleteFilm(title:String){
        this.storage.remove(title)
    }

}
