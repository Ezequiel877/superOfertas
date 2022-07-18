package com.example.kampasmobil2.UI.Home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kampasmobil2.Core.Result
import com.example.kampasmobil2.Core.adapterCustoms.adapterDetalles
import com.example.kampasmobil2.Core.visivility.hide
import com.example.kampasmobil2.Core.visivility.show
import com.example.kampasmobil2.Data.Home.DataSourceHome
import com.example.kampasmobil2.Data.Home.Home.iu.OnCartListener
import com.example.kampasmobil2.DataSource.Orden
import com.example.kampasmobil2.DataSource.direccion
import com.example.kampasmobil2.R
import com.example.kampasmobil2.UI.Home.Implement.HomeImp
import com.example.kampasmobil2.databinding.FragmentBlank4Binding
import com.example.kampasmobil2.model.HomeScreemFactory
import com.example.kampasmobil2.model.viewModelPost
import com.google.firebase.auth.FirebaseAuth

class Blank4 : Fragment(R.layout.fragment_blank4), adapterDetalles.OnModelClick, OnCartListener {

    private val args by navArgs<Blank4Args>()
    private lateinit var binding: FragmentBlank4Binding
    private val viewModel: viewModelPost by activityViewModels<viewModelPost> {
        HomeScreemFactory(
            HomeImp(
                DataSourceHome()
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBlank4Binding.bind(view)
        binding.textViewId.text = args.id
        Log.d("TEXTVIEWID", "onViewCreated: + $id")
        val id = binding.textViewId.text.toString()
        viewModel.getOfertas(id).observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Loading -> {
                    Log.d("FIREBASE", "onViewCreatedloading: ${it.toString()}")
                    binding.deltarelative.show()
                }
                is Result.Succes -> {
                    Log.d("DETALLESDELASOFEERTAS", "onViewCreated: ${it.data}")
                    binding.deltarelative.hide()

                    binding.detallesO.adapter = adapterDetalles(it.data, this)

                }
                is Result.Failure -> {
                    binding.deltarelative.hide()
                    Log.d("FIREBASE", "onViewCreated:${it.exception} ")
                    Toast.makeText(
                        requireContext(),
                        "ocurrio un error:${it.exception}",
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }
        })
        buttonRecycler()


        val userid = FirebaseAuth.getInstance().currentUser

    }


    fun setTotal(total: Int) {
        binding.tevcarrito.text = "${total}"
    }


    private fun buttonRecycler() {

        binding.btnVerCarrito.setOnClickListener {
            val fragmentDireccion =
                Blank4Directions.actionBlank4ToCardFragmentDialog(binding.textViewId.text.toString())
            findNavController().navigate(fragmentDireccion)
        }
    }
    override fun onmodelClick(model: Orden) {
        val action = Blank4Directions.actionBlank4ToFragmentDetalleProducto(
            model.nombre,
            model.descripcion
        )
        findNavController().navigate(action)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.fragmentOrden -> {
                val navegacion=Blank4Directions.actionBlank4ToFragmentOrden(binding.textViewId.text.toString())
                findNavController().navigate(navegacion)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setQuanty(produc: Orden) {
        TODO("Not yet implemented")
    }

    override fun showTotal(total: Int) {
        setTotal(total)
    }

    override fun onmodelClick(model: direccion) {
        TODO("Not yet implemented")
    }
}