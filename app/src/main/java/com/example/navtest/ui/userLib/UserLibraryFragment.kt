package com.example.navtest.ui.userLib

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navtest.R
import com.example.navtest.adapters.BookAdapter
import com.example.navtest.booksData.Book
import com.example.navtest.databinding.FragmentUserLibraryBinding

class UserLibraryFragment: Fragment() {
    private lateinit var binding: FragmentUserLibraryBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BookAdapter
    private val books: MutableList<Book> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = BookAdapter(books) { book -> onBookClicked(book) }
        recyclerView.adapter = adapter

        // Проверяем, есть ли у пользователя книги в библиотеке
        if (books.isEmpty()) {
            // Если у пользователя нет книг, отображаем кнопки для добавления новых книг
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
            // Если у пользователя есть книги, скрываем кнопки
            binding.btnAddBook.visibility = View.GONE
            binding.btnCreateBook.visibility = View.GONE
        }

        // Загрузка списка книг пользователя из базы данных или другого источника данных
        loadUserBooks()
    }

    private fun loadUserBooks() {
        // Здесь загружаем книги пользователя из базы данных или другого источника данных
        // Например:
        // books.addAll(getUserBooks())
        // adapter.notifyDataSetChanged()
    }

    private fun onBookClicked(book: Book) {
        // Обработка нажатия на книгу в списке
        // Открыть экран с подробной информацией о книге
        // Например:
        // val action = UserLibraryFragmentDirections.actionUserLibraryFragmentToBookDetailsFragment(book)
        // findNavController().navigate(action)
    }
}