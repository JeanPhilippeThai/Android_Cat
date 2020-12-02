package com.ismin.android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_film_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val FILM_TO_SHOW_EXTRA_KEY = "FILM_TO_SHOW_EXTRA_KEY"

class MainActivity : AppCompatActivity(), FilmCreator, FilmCallback {
    private val TAG = MainActivity::class.simpleName
    private val filmshelf = Filmshelf()
    private lateinit var filmService: FilmService;
    private val filmInfoActivityRequestCode = 1;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://ruinan-bookshelf.cleverapps.io")
            .build()

        filmService = retrofit.create(FilmService::class.java)

        filmService.getAllFilms().enqueue(object : Callback<ArrayList<Film>> {
            override fun onResponse(
                call: Call<ArrayList<Film>>,
                response: Response<ArrayList<Film>>
            ) {
                val allFilms = response.body()
                if (allFilms == null) {
                    println("ss")
                }
                allFilms?.forEach {
                    println(it.url_post)
                    filmshelf.addFilm(it)
                }
                displayList()
            }

            override fun onFailure(call: Call<ArrayList<Film>>, t: Throwable) {
                displayErrorToast(t)
            }
        })


    }


    private fun displayErrorToast(t: Throwable) {
        Toast.makeText(
            applicationContext,
            "Network error ${t.localizedMessage}",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun displayList() {
        val filmListFragment = FilmListFragment.newInstance(filmshelf.getAllFilms(),this)
        supportFragmentManager.beginTransaction()
            .replace(R.id.a_main_lyt_container, filmListFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }

    fun goToCreation(view: View) {
        val createFilmFragment = CreateFilmFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.a_main_lyt_container, createFilmFragment)
            .addToBackStack("createFilmFragment")
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
        a_main_btn_creation.visibility = View.GONE
    }

    /////////////////////////////////////////Gu

    fun Delete(view:View){
        filmService.deleteFilm(view.tag.toString()).enqueue(){
            onResponse={
                filmshelf.deleteFilm(view.tag.toString())
                displayList()
            }
            onFailure={
                if(it!=null){
                    displayErrorToast(it)
                }
            }
        }
    }
    /////////////////////////////////////////Gu
    override fun onFilmCreated(film: Film) {
        filmService.createFilm(film).enqueue {
            onResponse = {
                filmshelf.addFilm(it.body()!!)
                closeCreateFragment()
            }
            onFailure = {
                if (it != null) {
                    displayErrorToast(it)
                }
            }
        }
    }

    override fun closeCreateFragment() {
        displayList();
        a_main_btn_creation.visibility = View.VISIBLE
    }


    override fun goToFilm(film: Film) {
        val intent = Intent(this, FilmInfoActivity::class.java)
        intent.putExtra("EXTRA_FILM", film)
        startActivityForResult(intent, this.filmInfoActivityRequestCode)
    }

}