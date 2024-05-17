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
        val args = BookDetailFragmentArgs.fromBundle(requireArguments())
        binding.tvBookTitle.text = args.bookTitle
        binding.tvBookAuthor.text = args.bookAuthor
        binding.tvBookGenres.text = args.bookGenre

        // Загрузка обложки книги с использованием Glide
        if (args.bookCoverUrl.isNotEmpty()) {
            Glide.with(this)
                .load(args.bookCoverUrl)
                .into(binding.ivBookCover)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}