package com.example.kampasmobil2.UI.Carrrito.Orden

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kampasmobil2.Core.Result
import com.example.kampasmobil2.Core.adapterCustoms.Adapterdireccion
import com.example.kampasmobil2.Data.Home.DataSourceHome
import com.example.kampasmobil2.Data.Home.Home.iu.OnCartListener
import com.example.kampasmobil2.DataSource.Orden
import com.example.kampasmobil2.DataSource.direccion
import com.example.kampasmobil2.R
import com.example.kampasmobil2.UI.Home.Implement.HomeImp
import com.example.kampasmobil2.databinding.FragmentDeliveryBinding
import com.example.kampasmobil2.model.HomeScreemFactory
import com.example.kampasmobil2.model.viewModelPost

class FragmentDelicery : Fragment(R.layout.fragment_delivery), OnCartListener {

    private val args by navArgs<FragmentDeliceryArgs>()
    private lateinit var binding: FragmentDeliveryBinding
    private val viewModel: viewModelPost by activityViewModels<viewModelPost> {
        HomeScreemFactory(
            HomeImp(
                DataSourceHome()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDeliveryBinding.bind(view)
        binding.flotingNextBoton.setOnClickListener {
            val navegation = FragmentDeliceryDirections.actionFragmentDeliceryToFragmentDireccion(
                binding.textDireccionCliente.text.toString(),
                "",
                ""
            )
            findNavController().navigate(navegation)
        }
        val firebase = com.google.firebase.auth.FirebaseAuth.getInstance().uid
        if (firebase != null) {
            viewModel.getDireccion(firebase.toString())
                .observe(viewLifecycleOwner, Observer { result ->
                    when (result) {
                        is Result.Loading -> {
                            Toast.makeText(requireContext(), "${result}", Toast.LENGTH_SHORT).show()
                        }
                        is Result.Succes -> {
                            binding.detallesO.adapter = Adapterdireccion( result.data, this)
                        }
                        is Result.Failure -> {
                            Toast.makeText(
                                requireContext(),
                                "${result.exception}",
                                Toast.LENGTH_SHORT
                            ).show()
                            Log.d("errordireccion", "getDireccion: ${result.exception}")
                        }
                    }
                })
        }
    }

    override fun setQuanty(produc: Orden) {
        TODO("Not yet implemented")
    }

    override fun showTotal(total: Int) {
        TODO("Not yet implemented")
    }

    override fun onmodelClick(model: direccion) {
        val navegation= FragmentDeliceryDirections.actionFragmentDeliceryToFragmentCarrrito(
            model.ubicacion,
            model.direccion,
            args.idcommers
        )
        findNavController().navigate(navegation)
    }


}
