package com.example.navtest.ui.bookSearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.navtest.adapters.BookAdapter
import com.example.navtest.booksData.Book
import com.example.navtest.databinding.FragmentBookSearchBinding
import com.google.firebase.firestore.FirebaseFirestore

class BookSearchFragment : Fragment() {

    private lateinit var binding: FragmentBookSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookSearchBinding.inflate(inflater, container, false)

        binding.recyclerView.adapter = BookAdapter { book: Book ->

        }

        binding.btnSearch.setOnClickListener {
            val query = binding.btnSearch.text.toString().trim()
            searchBooks(query)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun searchBooks(query: String) {
        val db = FirebaseFirestore.getInstance()
        val booksCollection = db.collection("books")

        // Выполните запрос к базе данных Firestore для поиска книг по запросу
        // Например, используйте метод whereEqualTo() для поиска книг по названию, автору или другим параметрам

        // Пример запроса к базе данных Firestore
        booksCollection
            .whereEqualTo("title", query) // Здесь можно использовать нужный вам параметр для поиска
            .get()
            .addOnSuccessListener { documents ->
                // Обработка результатов поиска
                for (document in documents) {
                    // Здесь можно получить данные каждой найденной книги и выполнить необходимые действия
                }
            }
            .addOnFailureListener { exception ->
                // Обработка ошибок при выполнении запроса
            }
    }
}


