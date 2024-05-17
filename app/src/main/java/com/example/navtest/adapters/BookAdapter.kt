package com.example.navtest.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.navtest.R
import com.example.navtest.booksData.Book
import com.example.navtest.databinding.ItemBookBinding
import com.example.navtest.kotlinUtils.capitalized
import java.util.Locale

class BookAdapter(private val onItemClicked: (Book) -> Unit) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private var books: List<Book> = listOf()

    fun setBooks(books: List<Book>) {
        this.books = books
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book)
    }

    override fun getItemCount() = books.size

    inner class BookViewHolder(private val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {

            binding.titleTextView.text = book.title.split(' ').joinToString(" ") { it.capitalized() }
            binding.authorTextView.text = book.author.split(' ').joinToString(" ") { it.capitalized() }
            binding.genresTextView.text = book.genre.joinToString(", "){ it.capitalized() }

            if (book.coverUrl.isNotEmpty()) {
                Glide.with(binding.coverImageView.context)
                    .load(book.coverUrl)
                    .into(binding.coverImageView)
            }

            binding.root.setOnClickListener {
                onItemClicked(book)
            }
        }
    }
}
