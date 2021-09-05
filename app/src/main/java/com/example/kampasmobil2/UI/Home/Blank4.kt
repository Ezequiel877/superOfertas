package com.example.kampasmobil2.UI.Home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.kampasmobil2.Core.Load
import com.example.kampasmobil2.Core.Result
import com.example.kampasmobil2.Core.adapterCustoms.adapterDetalles
import com.example.kampasmobil2.Core.visivility.hide
import com.example.kampasmobil2.Core.visivility.show
import com.example.kampasmobil2.Data.Home.DataSourceHome
import com.example.kampasmobil2.DataSource.DataSource
import com.example.kampasmobil2.R
import com.example.kampasmobil2.UI.Home.Implement.HomeImp
import com.example.kampasmobil2.databinding.FragmentBlank4Binding
import com.example.kampasmobil2.model.HomeScreemFactory
import com.example.kampasmobil2.model.viewModelPost
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging

class Blank4 : Fragment(R.layout.fragment_blank4) {
    private val args by navArgs<Blank4Args>()
    private lateinit var binding: FragmentBlank4Binding
    private val viewModel: viewModelPost by activityViewModels<viewModelPost> {
        HomeScreemFactory(
            HomeImp(
                DataSourceHome()
            )
        )
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBlank4Binding.bind(view)
        binding.textViewId.text = args.detalles
        binding.favorite.text = args.id
        binding.notif.text = args.cofertas
        binding.carrito.text = args.ubicacion
        binding.Imagencrop.Load(args.precios)
        binding.backList.Load(args.imagen)
        val id=binding.textViewId
        binding.botonNotificacion.setOnClickListener {
            Firebase.messaging.subscribeToTopic("$id")
                .addOnCompleteListener { task ->
                    var msg = getString(R.string.open)
                    if (!task.isSuccessful) {
                        msg = getString(R.string.close)
                    }

                    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
                }

        }

        val userid = FirebaseAuth.getInstance().currentUser
        binding.botonFav.setOnClickListener {
            binding.let {fracagment->
                userid?.displayName?.let { userid ->
                    val datos = DataSource(
                        precios = fracagment.favorite.text.toString()
                            .trim(),
                        Cofertas = fracagment.carrito.text.toString()
                            .trim(),
                        detalles = fracagment.textViewId.text.toString().trim(),
                        id = userid,
                        ImagenD = fracagment.backList.toString(),
                        imagen = fracagment.Imagencrop.toString()

                    )
                    setFavorite(datos)
                }
            }
        }

        viewModel.favoristos().observe(viewLifecycleOwner, Observer {datos->
            when (datos) {
                is Result.Loading -> {
                    binding.deltarelative.show()
                    Toast.makeText(requireContext(), "loading", Toast.LENGTH_SHORT).show()


                }
                is Result.Succes -> {
                    binding.deltarelative.hide()
                    binding.detallesO.adapter = adapterDetalles(datos.data)
                    Toast.makeText(requireContext(), "SUCCESS", Toast.LENGTH_SHORT).show()

                }
                is Result.Failure -> {
                    binding.deltarelative.hide()

                    Toast.makeText(
                        requireContext(),
                        "ocurrio un error:${datos.exception}",
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }
        })

    }

    fun setFavorite(comercio: DataSource) {
        val firebase = FirebaseFirestore.getInstance()
        firebase.collection("like").document().set(comercio).addOnSuccessListener {
            Toast.makeText(requireContext(), "sent favorite", Toast.LENGTH_SHORT).show()
        }
    }

    fun sent(binding: FragmentBlank4Binding) {
        val userid = FirebaseAuth.getInstance().currentUser
        binding.let {
            userid?.displayName.let { userid ->
                val datos = DataSource(
                    precios = it.favorite.text.toString()
                        .trim(),
                    Cofertas = it.carrito.text.toString()
                        .trim(),
                    detalles = it.textViewId.text.toString().trim(),
                    id = userid.toString(),
                    ImagenD = it.Imagencrop.toString()

                )
            }
        }
    }

}





