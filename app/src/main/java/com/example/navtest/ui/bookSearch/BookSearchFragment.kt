package com.example.navtest.ui.bookSearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.navtest.R
import androidx.navigation.fragment.findNavController
import com.example.navtest.adapters.BookAdapter
import com.example.navtest.booksData.Book
import com.example.navtest.databinding.FragmentBookSearchBinding
import com.google.firebase.firestore.FirebaseFirestore

class BookSearchFragment : Fragment() {

    private var _binding: FragmentBookSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var bookAdapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookSearchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookAdapter = BookAdapter{ book ->
            val action = BookSearchFragmentDirections.actionNavBookSearchToNavBookDetail(
                book.title, book.author, book.genre.joinToString(", "), book.coverUrl
            )
            findNavController().navigate(action)
        }

        binding.recyclerView.adapter = bookAdapter

        binding.btnSearch.setOnClickListener {
            if(binding.etSearch.text.isEmpty())
                Toast.makeText(context, String.format("%s", getString(R.string.error_on_empty_search)),
                    Toast.LENGTH_SHORT).show()
            else {
                val query = binding.etSearch.text.toString().trim().lowercase()
                searchBooks(query)
            }
        }

    }

    private fun searchBooks(query: String) {
        val db = FirebaseFirestore.getInstance()
        val booksCollection = db.collection("books")

        booksCollection //  TODO("Make it search for any field")
            .orderBy("title").startAt(query).endAt("$query~")
            .get()
            .addOnSuccessListener { documents ->

                val books = documents.map { document ->
                    document.toObject(Book::class.java)
                }

                bookAdapter.setBooks(books)
            }
            .addOnFailureListener { exception ->
                Toast.makeText(context, String.format("%s%s", getString(R.string.search_book_et),
                    exception.message), Toast.LENGTH_SHORT).show()
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


