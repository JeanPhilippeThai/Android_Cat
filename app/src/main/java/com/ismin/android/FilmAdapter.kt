package com.ismin.android

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class FilmAdapter(private val films: ArrayList<Film>,private val c: AppCompatActivity) : RecyclerView.Adapter<FilmViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.row_film, parent, false)
        return FilmViewHolder(row)
    }


    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val (title, author, date,url_post,url_wiki) = this.films[position]
        holder.txvTitle.text = title
        holder.txvAuthor.text = author
        holder.txvDate.text = date
        holder.txvInfo.tag=url_wiki
        if(url_post!="") {//gu
            Picasso.with(c).load(url_post).into(holder.txvImage)
        }
        holder.txvDelete.tag=title //gu

    }

    override fun getItemCount(): Int {
        return this.films.size
    }

    fun updateItem(filmsToDisplay: List<Film>) {
        films.clear();
        films.addAll(filmsToDisplay)
        notifyDataSetChanged();
    }
}
