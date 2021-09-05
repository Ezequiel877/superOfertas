package com.example.kampasmobil2.UI.LoginSetup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.kampasmobil2.Core.Result
import com.example.kampasmobil2.Data.Home.Home.iu.usuario.usuarioDtaSource
import com.example.kampasmobil2.R
import com.example.kampasmobil2.UI.LoginSetup.Firebase.FirebaseAuth
import com.example.kampasmobil2.databinding.FragmentBlankLoginBinding
import com.example.kampasmobil2.model.modelLoginSetup
import com.example.kampasmobil2.model.modelRegistro

class BlankLogin : Fragment(R.layout.fragment_blank_login) {
    private lateinit var binding:FragmentBlankLoginBinding
    private val viewModel by viewModels<modelLoginSetup> {
        modelLoginSetup.repo(FirebaseAuth(usuarioDtaSource()))
    }
    private val firebaseAuth by lazy { com.google.firebase.auth.FirebaseAuth.getInstance() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentBlankLoginBinding.bind(view)
        changeClientes()
        bindingCheck()
        goingLoging()
    }

    private fun changeClientes() {
        firebaseAuth.currentUser?.let {
            findNavController().navigate(R.id.action_blankLogin_to_blank1)
        }
    }

    private fun bindingCheck() {
        binding.buttonIn.setOnClickListener {
            val email = binding.InPutText.text.toString().trim()
            val contraseña = binding.InPutTextAdress.text.toString().trim()
            reliaceEmail_Passwork(email, contraseña)
            reliaceRegistro(email, contraseña)
        }

    }
    private fun goingLoging(){
        binding.singUp.setOnClickListener {
            findNavController().navigate(R.id.action_blankLogin_to_blankRegister)

        }
    }

    private fun reliaceEmail_Passwork(email: String, contraseña: String) {
        if (email.isEmpty()) {
            binding.InPutText.error = "ERROR email is empty"
            return
        }
        if (contraseña.isEmpty()) {
            binding.InPutText.error = "ERROR contraseña is empty"
            return

        }
    }

    private fun reliaceRegistro(email: String, password: String) {
        viewModel.singip(email, password).observe(viewLifecycleOwner, Observer { result->
            when(result){
                is Result.Loading->{
                    binding.progresRigt.visibility=View.VISIBLE
                    binding.buttonIn.isEnabled=false
                }
                is Result.Succes->{
                    binding.progresRigt.visibility=View.GONE
                    findNavController().navigate(R.id.action_blankLogin_to_blank1)

                }
                is Result.Failure->{
                    binding.progresRigt.visibility=View.GONE
                    binding.buttonIn.isEnabled=true
                }

            }
        })

    }
}
