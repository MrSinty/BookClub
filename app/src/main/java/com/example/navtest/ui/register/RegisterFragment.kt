package com.example.navtest.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navtest.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.example.navtest.databinding.FragmentRegisterBinding

class RegisterFragment: Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        binding.btnCreateAcc.setOnClickListener {
            val email = binding.etEmailAddress.text.toString().trim()
            val login = binding.etUserLogin.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val checkPassword = binding.etPasswordConfirm.text.toString().trim()

            if (email.isEmpty() || password.isEmpty() || login.isEmpty()) {
                binding.tvError.text = String.format("%s", getString(R.string.error_empty_reg))
                binding.tvError.visibility = View.VISIBLE
            }
            else if (password != checkPassword){
                binding.tvError.text = String.format("%s", getString(R.string.error_not_equal_reg))
                binding.tvError.visibility = View.VISIBLE
            }
            else {
                register(email, password)
            }
        }

        binding.btnToLogin.setOnClickListener {
            findNavController().navigate(R.id.nav_login)
        }
    }

    private fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {

                    val user = auth.currentUser
                    binding.tvError.visibility = View.INVISIBLE
                    findNavController().navigate(R.id.nav_login)
                } else {

                    binding.tvError.text = task.exception?.message
                    binding.tvError.visibility = View.VISIBLE
                }
            }
    }
}