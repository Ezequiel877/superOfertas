package com.example.kampasmobil2.UI.LoginSetup

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.preference.PreferenceManager
import com.example.kampasmobil2.Core.Result
import com.example.kampasmobil2.Core.constantes.constantes
import com.example.kampasmobil2.Data.Home.Home.iu.usuario.usuarioDtaSource
import com.example.kampasmobil2.R
import com.example.kampasmobil2.UI.Home.FragmentDireccionArgs
import com.example.kampasmobil2.UI.Home.FragmentDireccionDirections
import com.example.kampasmobil2.UI.LoginSetup.Firebase.RigisteImp
import com.example.kampasmobil2.databinding.FragmentBlankRegisterBinding
import com.example.kampasmobil2.model.UthRegistro
import com.example.kampasmobil2.model.modelRegistro
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging

class BlankRegister : Fragment(R.layout.fragment_blank_register) {
    private lateinit var binding: FragmentBlankRegisterBinding
    private val viewModel by viewModels<modelRegistro> {
        UthRegistro(RigisteImp(usuarioDtaSource()))
    }
    private val args by navArgs<BlankRegisterArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBlankRegisterBinding.bind(view)
        getSeting()

    }

    private fun getSeting() {
        binding.buttonOn.setOnClickListener {
            val name = binding.InPutTextUsername.text.toString().trim()
            val email = binding.InPutTextEmail.text.toString().trim()
            val passwork = binding.Passwork.text.toString().trim()
            val confirm = binding.Confirm.text.toString().trim()
            createUser(email, passwork, name)
            if (passwork != confirm) {
                binding.Passwork.error = "no hay coincidencia"
                binding.Confirm.error = "no hay coincidencia"

            }

            val prefence =
                android.preference.PreferenceManager.getDefaultSharedPreferences(this.context)
            val token = prefence.getString(constantes.TOKEN_ID, null)


            token?.let {
                val bd = FirebaseFirestore.getInstance()
                val tokenMa = hashMapOf(Pair(constantes.TOKEN_ID, token))
                bd.collection("user").add(tokenMa)
                    .addOnSuccessListener {
                        Log.i("register token", token)
                        prefence.edit {
                            putString(constantes.Firabe_Token, null)
                                .apply()
                        }

                    }
                    .addOnFailureListener {
                        Log.i("failed", token)
                    }
            }
            return@setOnClickListener
        }
    }

    private fun createUser(email: String, passwork: String, name: String) {
        viewModel.singip(email, passwork, name).observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progresLigt.visibility = View.VISIBLE
                    binding.buttonOn.isEnabled = false
                }
                is Result.Succes -> {
                    binding.progresLigt.visibility = View.GONE
                    val user =
                        BlankRegisterDirections.actionBlankRegisterToFragmentDireccion2("", "", "")

                    findNavController().navigate(user)
                }
                is Result.Failure -> {
                    binding.progresLigt.visibility = View.GONE
                    binding.buttonOn.isEnabled = false
                    Toast.makeText(
                        requireContext(),
                        "no carga:${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }
}