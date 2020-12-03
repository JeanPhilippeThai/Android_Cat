package com.ismin.android

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.RuntimeException

private const val ARG_BOOKS = "ARG_BOOKS"

class FilmListFragment(c: AppCompatActivity) : Fragment(), OnFilmItemClickListener {
    private lateinit var films: ArrayList<Film>
    private lateinit var rcvFilms: RecyclerView
    private lateinit var listener: FilmCallback
    public var con:AppCompatActivity=c

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            films = it.getSerializable(ARG_BOOKS) as ArrayList<Film>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val rootView = inflater.inflate(R.layout.fragment_film_list, container, false)
        this.rcvFilms = rootView.findViewById(R.id.f_film_list_rcv_films)
        this.rcvFilms.adapter = FilmAdapter(films, con)
        val linearLayoutManager = LinearLayoutManager(context)
        this.rcvFilms.layoutManager = linearLayoutManager
        val dividerItemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
        this.rcvFilms.addItemDecoration(dividerItemDecoration)

        return rootView;
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FilmCallback){
            listener = context
        }else{
            throw RuntimeException("$context must implement FilmCallback")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(films: List<Film>,c: AppCompatActivity) =
            FilmListFragment(c).apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_BOOKS, ArrayList(films))
                }
            }
    }

    override fun onFilmItemClickListener(position: Int) {
        val clickedFilm: Film = films[position]
        listener.goToFilm(clickedFilm)
    }

}