package com.example.navtest.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navtest.R
import com.example.navtest.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmailAddress.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                binding.tvError.text = "Please enter email and password"
                binding.tvError.visibility = View.VISIBLE
            } else {
                signIn(email, password)
            }
        }

        binding.btnToReg.setOnClickListener {
            findNavController().navigate(R.id.nav_register)
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Вход успешен
                    val user = auth.currentUser
                    binding.tvError.visibility = View.INVISIBLE
                    // Переход на следующий экран, например, главный экран
                    findNavController().navigate(R.id.nav_home)
                } else {
                    // Вход не удался, обработка ошибки
                    // Например, вывод сообщения об ошибке
                    binding.tvError.text = task.exception?.message
                }
            }
    }
}