package com.ismin.android

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.EditText
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.google.android.material.transition.FadeThroughProvider

class CreateFilmFragment : Fragment() {
    private var activity: FilmCreator? = null;
    private lateinit var edtTitle: EditText
    private lateinit var edtAuthor: EditText
    private lateinit var edtDate: EditText
    private lateinit var edturl_wiki: EditText
    private lateinit var edturl_post: EditText
    private lateinit var card: CardView
    private lateinit var rootLayout: ViewGroup
    private lateinit var blackView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_create_film, container, false)

        rootLayout = rootView.findViewById(R.id.f_create_film_root_layout)
        edtTitle = rootView.findViewById(R.id.f_create_film_edt_title)
        edtAuthor = rootView.findViewById(R.id.f_create_film_edt_author)
        edtDate = rootView.findViewById(R.id.f_create_film_edt_date)
        edturl_post = rootView.findViewById(R.id.f_create_film_edt_url_post)//gu
        edturl_wiki = rootView.findViewById(R.id.f_create_film_edt_url_wiki)//gu
        card = rootView.findViewById(R.id.f_create_film_card)
        blackView = rootView.findViewById(R.id.f_create_film_black_view)

        rootView.setOnClickListener { activity?.closeCreateFragment() }
        rootView.findViewById<Button>(R.id.f_create_film_btn_save).setOnClickListener {
            saveFilm()
        }

        ObjectAnimator.ofFloat(card, "translationY", 300f, 0f)
            .apply {
                interpolator = AccelerateDecelerateInterpolator()
                duration = 300
                start()
            }

        FadeThroughProvider().createAppear(rootLayout, card)?.start()

        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()

        ObjectAnimator.ofFloat(card, "translationY", 0f, 300f)
            .apply {
                interpolator = AccelerateDecelerateInterpolator()
                duration = 100
                start()
            }

        FadeThroughProvider().createDisappear(rootLayout, card)?.start()
    }

    override fun onAttach(context: Context) {
        if (context is FilmCreator) {
            activity = context
        }
        super.onAttach(context)
    }

    fun saveFilm() {
        activity?.onFilmCreated(
            Film(
                edtTitle.text.toString(),
                edtAuthor.text.toString(),
                edtDate.text.toString(),
                edturl_post.text.toString(),//gu
                edturl_wiki.text.toString()//gu
            )
        )
    }
}

interface FilmCreator {
    fun onFilmCreated(film: Film)
    fun closeCreateFragment()
}