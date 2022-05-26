package com.example.kampasmobil2.Card

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kampasmobil2.Core.Utils.SharePrf
import com.example.kampasmobil2.Core.adapterCustoms.ProductCartAdapter
import com.example.kampasmobil2.Data.Home.Home.iu.OnCartListener
import com.example.kampasmobil2.DataSource.DataSource
import com.example.kampasmobil2.DataSource.direccion
import com.example.kampasmobil2.R
import com.example.kampasmobil2.databinding.FragmentCarBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CardFragmentDialog : BottomSheetDialogFragment(), OnCartListener {

    private var bindinf: FragmentCarBinding? = null
    private lateinit var mBottonSheet: BottomSheetBehavior<*>
    private lateinit var adapter: ProductCartAdapter
    private var productSelectect: DataSource? = null
    var share: SharePrf? = null
    var IDCOMMER: String=""
    var selectProducto = ArrayList<DataSource>()
    var json = Gson()
    private val args by navArgs<CardFragmentDialogArgs>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        bindinf = FragmentCarBinding.inflate(LayoutInflater.from(context))
        bindinf?.let {
            val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
            bottomSheetDialog.setContentView(it.root)
            mBottonSheet = BottomSheetBehavior.from(it.root.parent as View)
            mBottonSheet.state = BottomSheetBehavior.STATE_EXPANDED
            gotoShopping()
            setupCancelBnt()
            recycler()
            return bottomSheetDialog
        }


        return super.onCreateDialog(savedInstanceState)
    }

    private fun setupCancelBnt() {
        bindinf?.let {
            it.imagenCancel.setOnClickListener {
                mBottonSheet.state = BottomSheetBehavior.STATE_HIDDEN
            }
        }
    }

    fun setTotal(total: Int) {
        bindinf!!.textTotal.text = "${total}"
    }

    private fun gotoShopping() {
        bindinf!!.idFlag.setOnClickListener {
            val navegacion=CardFragmentDialogDirections.actionCardFragmentDialogToFragmentDelicery3(
                args.idComers
            )
            findNavController().navigate(navegacion)
            onDestroy()
        }
    }


    private fun recycler() {
        share = SharePrf(requireContext())
        var doblw = 0
        if (!share?.getData("orden").isNullOrBlank()) {
            val type = object : TypeToken<ArrayList<DataSource>>() {}.type
            selectProducto = json.fromJson(share?.getData("orden"), type)

            bindinf?.let {
                adapter = context?.let { it1 -> ProductCartAdapter(it1, selectProducto, this) }!!
                it.recyclerCarf.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = this@CardFragmentDialog.adapter
                }
                for (s in selectProducto) {
                    doblw += (s.precioProduc * s.precios)
                }
                setTotal(doblw)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        bindinf = null
    }

    override fun setQuanty(produc: DataSource) {
        TODO("Not yet implemented")
    }

    override fun showTotal(total: Int) {
        setTotal(total)
    }

    override fun onmodelClick(model: direccion) {

    }
}
