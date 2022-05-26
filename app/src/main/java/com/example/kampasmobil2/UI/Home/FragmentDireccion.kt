package com.example.kampasmobil2.UI.Home

import android.app.Activity
import android.os.Bundle
import android.preference.PreferenceManager
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
import com.example.kampasmobil2.Core.Result
import com.example.kampasmobil2.Core.adapterCustoms.adapterDetalles
import com.example.kampasmobil2.Core.constantes.constantes
import com.example.kampasmobil2.Core.visivility.hide
import com.example.kampasmobil2.Core.visivility.show
import com.example.kampasmobil2.Data.Home.Home.iu.usuario.usuarioDtaSource
import com.example.kampasmobil2.R
import com.example.kampasmobil2.UI.LoginSetup.Firebase.FirebaseAuth
import com.example.kampasmobil2.UI.LoginSetup.Firebase.RigisteImp
import com.example.kampasmobil2.databinding.FragmentDireccionBinding
import com.example.kampasmobil2.model.UthRegistro
import com.example.kampasmobil2.model.modelRegistro
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class FragmentDireccion : Fragment(R.layout.fragment_direccion) {

    private lateinit var binding: FragmentDireccionBinding
    private val args by navArgs<FragmentDireccionArgs>()
    val TAG = "FragmentDireccion"
    private val viewModel by viewModels<modelRegistro> {
        UthRegistro(RigisteImp(usuarioDtaSource()))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDireccionBinding.bind(view)
        if (args != null) {
            binding.direccionUbicacion.text = "${args.ubicacion + args.direccion + args.ciudad}"

        }

        binding.direccionUbicacion.setOnClickListener {
            gotoMAP()
        }
        val user = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser?.uid.toString()

        binding.buttonOn.setOnClickListener {
            if (com.google.firebase.auth.FirebaseAuth.getInstance().currentUser?.uid.isNullOrEmpty()) {
                Toast.makeText(
                    context,
                    "Crea una direccion primero oprimiendo ´Crear direccion´",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                createUser(user, args.direccion, args.ubicacion)
                findNavController().navigate(R.id.fragmentDelicery)
            }
        }
        binding.buttoCrearDirec.setOnClickListener {
            findNavController().navigate(R.id.blank1)
            createUser(user, args.direccion, args.ubicacion)

            val usertoken = com.google.firebase.auth.FirebaseAuth.getInstance().uid
            if (usertoken != null) {

                val preference =
                    PreferenceManager.getDefaultSharedPreferences(this.context)
                val token = preference.getString(constantes.TOKEN_ID, null)
                token?.let {
                    val firebase = FirebaseFirestore.getInstance()
                    val tokenmap = hashMapOf(Pair(constantes.TOKEN_ID, token))
                    val db = firebase.collection("user").document(usertoken)
                    db.collection("token").add(tokenmap).addOnSuccessListener {
                        Log.d("TAGTOKENSEGUARDO", "createToken: $token")
                        preference.edit {
                            putString(constantes.TOKEN_ID, null)
                                .apply()
                        }
                    }.addOnFailureListener {
                        Log.d("TAGTOKENSEGUARDO", "createToken: $it")
                    }
                }
            }
        }

        Log.d(TAG, "onViewCreated: ${args.direccion} ")
        val firebase =
            com.google.firebase.auth.FirebaseAuth.getInstance().uid


    }

    private fun gotoMAP() {
        val nevegation = FragmentDireccionDirections.actionFragmentDireccionToMapCliente()
        findNavController().navigate(nevegation)

    }


    private fun createUser(name: String, direccion: String, ubicacion: String) {
        viewModel.getDireccion(name, direccion, ubicacion)
            .observe(viewLifecycleOwner, Observer { result ->
                when (result) {
                    is Result.Loading -> {
                    }
                    is Result.Succes -> {
                        binding.progresLigt.visibility = View.GONE
                        findNavController().navigate(R.id.fragmentDelicery)
                    }
                    is Result.Failure -> {
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