package com.ismin.android

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.RuntimeException


/**
 * A simple [Fragment] subclass.
 * Use the [AppInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class AppInfoFragment (c: AppCompatActivity): Fragment() {
    public var con: AppCompatActivity = c

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val rootView = inflater.inflate(R.layout.fragment_film_list, container, false)
        val linearLayoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)

        return rootView;
    }

    companion object {
        @JvmStatic
        fun newInstance(c: AppCompatActivity) =
            FilmListFragment(c).apply {
                arguments = Bundle().apply {
                }
            }
    }
}