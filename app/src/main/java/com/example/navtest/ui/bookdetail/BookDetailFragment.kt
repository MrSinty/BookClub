package com.example.navtest.ui.bookdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.navtest.R
import com.example.navtest.databinding.FragmentBookDetailBinding
import com.example.navtest.kotlinUtils.capitalized

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

        val args = BookDetailFragmentArgs.fromBundle(requireArguments())
        binding.tvBookTitle.text = args.bookTitle.split(' ').joinToString(" ") { it.capitalized() }
        binding.tvBookAuthor.text = String.format("%s%s", getString(R.string.book_detailed_autor_textv),
            args.bookAuthor.split(' ').joinToString(" ") { it.capitalized() })
        binding.tvBookGenres.text = String.format("%s%s", getString(R.string.book_detailed_genres_textv),
            args.bookGenre.split(", ").joinToString(", ") { it.capitalized() })

        if (args.bookCoverUrl.isNotEmpty()) {
            Glide.with(this)
                .load(args.bookCoverUrl)
                .error(android.R.drawable.stat_notify_error)
                .into(binding.ivBookCover)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}