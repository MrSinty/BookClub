package com.example.navtest.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SlideshowViewModel : ViewModel() {

    private var auth = Firebase.auth
    private val _text = MutableLiveData<String>().apply {
        value = auth.currentUser.toString()
    }
    val text: LiveData<String> = _text
}