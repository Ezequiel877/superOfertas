package com.example.kampasmobil2.UI.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.kampasmobil2.Core.Result
import com.example.kampasmobil2.Core.visivility.hide
import com.example.kampasmobil2.Core.visivility.show
import com.example.kampasmobil2.Data.Home.DataSourceHome
import com.example.kampasmobil2.R
import com.example.kampasmobil2.UI.Home.Implement.HomeImp
import com.example.kampasmobil2.databinding.FragmentBlank3Binding
import com.example.kampasmobil2.model.HomeScreemFactory
import com.example.kampasmobil2.model.viewModelPost
import java.util.Observer


class Blank3 : Fragment(R.layout.fragment_blank3) {
    private lateinit var binding:FragmentBlank3Binding
    private val viewModel: viewModelPost by activityViewModels<viewModelPost>{
        HomeScreemFactory(
            HomeImp(
        DataSourceHome()
    )
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBlank3Binding.bind(view)
        viewModel.favoristos().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            when (it) {
                is Result.Loading -> {
                    binding.deltaRelative.show()

                }
                is Result.Succes -> {
                    binding.deltaRelative.hide()


                }
                is Result.Failure -> {
                    binding.deltaRelative.hide()

                    Toast.makeText(
                        requireContext(),
                        "ocurrio un error:${it.exception}",
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }
        })
    }
 }