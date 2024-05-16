package com.example.navtest.booksData

data class Book(val id: String,
                val title: String,
                val author: String,
                val genres: List<String>,
                val coverImageUrl: String?,
                val year: Int
)
