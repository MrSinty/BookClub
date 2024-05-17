package com.example.navtest.ui.userLib

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navtest.R
import com.example.navtest.adapters.BookAdapter
import com.example.navtest.booksData.Book
import com.example.navtest.databinding.FragmentUserLibraryBinding
import com.google.firebase.firestore.FirebaseFirestore

class UserLibraryFragment: Fragment() {
    private lateinit var bookAdapter: BookAdapter
    private var _binding: FragmentUserLibraryBinding? = null
    private val binding get() = _binding!!
    private val books = mutableListOf<Book>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserLibraryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookAdapter = BookAdapter {book ->
            val action = UserLibraryFragmentDirections.actionNavBookGalleryToNavBookDetail(
                book.title, book.author, book.genre.joinToString(", "), book.coverUrl
            )
            findNavController().navigate(action)
        }

        binding.recyclerView.apply {
            adapter = bookAdapter
        }

        if (books.isEmpty()) {

            binding.tvNoBooks.visibility = View.VISIBLE
            binding.btnAddBook.visibility = View.VISIBLE
            binding.btnCreateBook.visibility = View.VISIBLE

            binding.btnAddBook.setOnClickListener {

                //findNavController().navigate(R.id.action_userLibraryFragment_to_searchBooksFragment)
            }

            binding.btnCreateBook.setOnClickListener {

                // findNavController().navigate(R.id.action_userLibraryFragment_to_createBookFragment)
            }
        } else {

            binding.tvNoBooks.visibility = View.GONE
            binding.btnAddBook.visibility = View.GONE
            binding.btnCreateBook.visibility = View.GONE

            loadUserBooks()
        }

    }

    private fun loadUserBooks() {
        val db = FirebaseFirestore.getInstance()
        val userBooksCollection = db.collection("user_books")

        userBooksCollection
            .get()
            .addOnSuccessListener { documents ->
                val books = documents.map { document ->
                    document.toObject(Book::class.java)
                }
                bookAdapter.setBooks(books)
            }
            .addOnFailureListener { exception ->
                Toast.makeText(context, String.format("%s%s", getString(R.string.error_on_search),
                    exception.message), Toast.LENGTH_SHORT).show()
            }
    }
}