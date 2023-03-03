package com.cnam.pushtotalk.presentation.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cnam.pushtotalk.R
import com.cnam.pushtotalk.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setObservers()
        setSignInButtonListener()
    }

    private fun setSignInButtonListener() = binding.btnLogin.setOnClickListener {
        val email = binding.txtLogin.text.toString()
        val password = binding.txtPassword.text.toString()
        if (email.isNotBlank() && password.isNotBlank()) {
            viewModel.signInToRainbow(email, password)
        }
    }

    private fun setObservers() {
        viewModel.signInStatus.observe(this) { signInStatus ->
            if (signInStatus == true) {
                try {
                    val action = R.id.action_loginFragment_to_canalListFragment
                    findNavController().navigate(action)
                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, R.string.toast_login_failed, Toast.LENGTH_SHORT).show()
            }
        }
    }
}