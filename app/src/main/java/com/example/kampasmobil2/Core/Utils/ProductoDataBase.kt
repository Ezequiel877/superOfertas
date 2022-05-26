package com.example.kampasmobil2.Core.Utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
/*
@Database(entities = [DataProductoEntity::class], version = 1)
abstract class ProductoDataBase : RoomDatabase() {

    abstract fun productoDao(): ProductosDao

    companion object {
        private var INSTANCIA: ProductoDataBase? = null

        fun getContext(context: Context): ProductoDataBase {
            INSTANCIA = INSTANCIA ?: Room.databaseBuilder(
                context.applicationContext,
                ProductoDataBase::class.java,
                "ProductoSeleccionados"
            ).build()
            return INSTANCIA!!
        }
    }
}*/