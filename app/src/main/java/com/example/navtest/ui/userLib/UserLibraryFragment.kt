package com.example.navtest.ui.userLib

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navtest.R
import com.example.navtest.adapters.BookAdapter
import com.example.navtest.booksData.Book
import com.example.navtest.databinding.FragmentUserLibraryBinding
import com.google.firebase.appcheck.internal.util.Logger.TAG
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

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
        TODO("OPEN BOOK DETAIL")
        }

        binding.recyclerView.apply {
            adapter = bookAdapter
        }

        // Проверяем, есть ли у пользователя книги в библиотеке
        if (books.isEmpty()) {

            Toast.makeText(context, "no books", Toast.LENGTH_SHORT).show()
            // Если у пользователя нет книг, отображаем кнопки для добавления новых книг
            binding.tvNoBooks.visibility = View.VISIBLE
            binding.btnAddBook.visibility = View.VISIBLE
            binding.btnCreateBook.visibility = View.VISIBLE

            binding.btnAddBook.setOnClickListener {
                // Открыть окно с поиском книг для добавления
                // Например:
                //findNavController().navigate(R.id.action_userLibraryFragment_to_searchBooksFragment)
            }

            binding.btnCreateBook.setOnClickListener {
                // Открыть окно создания новой книги
                // Например:
                // findNavController().navigate(R.id.action_userLibraryFragment_to_createBookFragment)
            }
        } else {

            Toast.makeText(context, "there are books", Toast.LENGTH_SHORT).show()
            // Если у пользователя есть книги, скрываем кнопки
            binding.tvNoBooks.visibility = View.GONE
            binding.btnAddBook.visibility = View.GONE
            binding.btnCreateBook.visibility = View.GONE

            loadUserBooks()
        }

    }

    private fun loadUserBooks() {
        val db = FirebaseFirestore.getInstance()
        val userBooksCollection = db.collection("user_books") // Коллекция с книгами пользователя

        Toast.makeText(context, "trying find books", Toast.LENGTH_SHORT).show()

        userBooksCollection
            .get()
            .addOnSuccessListener { documents ->
                val books = documents.map { document ->
                    document.toObject(Book::class.java)
                }
                bookAdapter.setBooks(books)
            }
            .addOnCompleteListener {
                Toast.makeText(context, "nothing found", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(context, "Error getting documents: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun onBookClicked(book: Book) {
        // Обработка нажатия на книгу в списке
        // Открыть экран с подробной информацией о книге
        // Например:
        // val action = UserLibraryFragmentDirections.actionUserLibraryFragmentToBookDetailsFragment(book)
        // findNavController().navigate(action)
    }
}