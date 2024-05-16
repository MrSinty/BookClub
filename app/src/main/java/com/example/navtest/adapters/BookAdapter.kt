package com.example.navtest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.navtest.R
import com.example.navtest.booksData.Book

class BookAdapter(
    private val books: List<Book>,
    private val onItemClick: (Book) -> Unit
) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book)
        holder.itemView.setOnClickListener { onItemClick(book) }
    }

    override fun getItemCount(): Int {
        return books.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val authorTextView: TextView = itemView.findViewById(R.id.authorTextView)
        private val genresTextView: TextView = itemView.findViewById(R.id.genresTextView)
        private val coverImageView: ImageView = itemView.findViewById(R.id.coverImageView)

        fun bind(book: Book) {
            titleTextView.text = book.title
            authorTextView.text = book.author
            genresTextView.text = book.genres.joinToString(", ")
            book.coverImageUrl?.let {
                Glide.with(itemView)
                    .load(it)
                    .placeholder(R.drawable.ic_launcher_background) // Заглушка для изображения
                    .error(R.drawable.ic_launcher_foreground) // Ошибка при загрузке изображения
                    .into(coverImageView)
            }
        }
    }
}