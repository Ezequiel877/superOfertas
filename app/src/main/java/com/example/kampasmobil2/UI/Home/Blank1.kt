package com.example.kampasmobil2.UI.Home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.kampasmobil2.Core.Result
import com.example.kampasmobil2.Core.adapterCustoms.adapter1Postprivate
import com.example.kampasmobil2.Core.visivility.hide
import com.example.kampasmobil2.Core.visivility.show
import com.example.kampasmobil2.Data.Home.DataSourceHome
import com.example.kampasmobil2.DataSource.Comercios
import com.example.kampasmobil2.R
import com.example.kampasmobil2.UI.Home.Implement.HomeImp
import com.example.kampasmobil2.databinding.FragmentBlank1Binding
import com.example.kampasmobil2.model.HomeScreemFactory
import com.example.kampasmobil2.model.viewModelPost
import com.google.android.gms.ads.AdRequest

class Blank1 : Fragment(R.layout.fragment_blank1), adapter1Postprivate.OnModelClick {

    private lateinit var binding: FragmentBlank1Binding
    private val viewModel: viewModelPost by activityViewModels<viewModelPost> {
        HomeScreemFactory(
            HomeImp(
                DataSourceHome()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBlank1Binding.bind(view)
        viewModel.getLeterOfert().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Loading -> {
                    binding.deltaRelative.show()


                }
                is Result.Succes -> {
                    binding.deltaRelative.hide()
                    binding.recicler.adapter = adapter1Postprivate(it.data, this)
                    Log.d("TAGREFERENCEHOME", "onViewCreated: ${it.data}")

                    adRequest()
                }
                is Result.Failure -> {
                    binding.deltaRelative.hide()
                    Toast.makeText(requireContext(),
                        "ocurrio un error:${it.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d("TAGREFERENCEHOME", "onViewCreated: ${it.exception}")
                }

            }
        })
    }

    private fun adRequest() {
        val adExample = AdRequest.Builder().build()
        binding.bannerx.loadAd(adExample)
    }

    override fun onmodelClick(model: Comercios) {
        val action = Blank1Directions.actionBlank1ToBlank4(
            model.id
        )
        findNavController().navigate(action)
    }

}

