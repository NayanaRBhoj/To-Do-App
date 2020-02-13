package com.example.to_do_app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class NotesListAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<NotesListAdapter.WordViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<Notes>() // Cached copy of words

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemTitleView: TextView = itemView.findViewById(R.id.textViewTitle)
        val wordItemDescView: TextView = itemView.findViewById(R.id.textViewDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = words[position]
        holder.wordItemTitleView.text = current.title
        holder.wordItemDescView.text = current.desc
    }

    internal fun setWords(words: List<Notes>) {
        this.words = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = words.size
}


