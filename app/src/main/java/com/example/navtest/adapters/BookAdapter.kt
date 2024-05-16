package com.example.navtest.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.navtest.R
import com.example.navtest.booksData.Book

class BookAdapter(private val onItemClicked: (Book) -> Unit) :
    ListAdapter<Book, BookAdapter.BookViewHolder>(BookViewHolder.BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)
    }

    class BookViewHolder(itemView: View, private val onItemClicked: (Book) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private val bookCoverImageView: ImageView = itemView.findViewById(R.id.coverImageView)
        private val bookTitleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val bookAuthorTextView: TextView = itemView.findViewById(R.id.authorTextView)

        fun bind(book: Book) {
            bookTitleTextView.text = book.title
            bookAuthorTextView.text = book.author

            Glide.with(itemView.context)
                .load(book.coverUrl)
                .into(bookCoverImageView)

            itemView.setOnClickListener {
                val bundle = Bundle().apply {
                    putString("bookTitle", book.title)
                    putString("bookAuthor", book.author)
                    putString("bookGenres", book.genres.joinToString(", "))
                    putString("bookCoverUrl", book.coverUrl)
                }

                TODO("сделать навигацию для bookDetail")
                //itemView.findNavController().navigate(R.id.action_bookSearchFragment_to_bookDetailFragment, bundle)
            }
        }

        class BookDiffCallback : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }
        }
    }
}