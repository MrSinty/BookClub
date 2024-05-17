package com.example.navtest.ui.bookdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.navtest.databinding.FragmentBookDetailBinding

class BookDetailFragment : Fragment() {

    private var _binding: FragmentBookDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Получение данных о книге из аргументов
        val bookTitle = arguments?.getString("title")
        val bookAuthor = arguments?.getString("author")
        val bookGenres = arguments?.getString("genres")
        val bookCoverUrl = arguments?.getString("coverUrl")

        // Установка данных на соответствующие элементы
        binding.tvBookTitle.text = bookTitle
        binding.tvBookAuthor.text = bookAuthor
        binding.tvBookGenres.text = bookGenres

        // Загрузка обложки книги с использованием Glide
        Glide.with(this)
            .load(bookCoverUrl)
            .into(binding.ivBookCover)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}