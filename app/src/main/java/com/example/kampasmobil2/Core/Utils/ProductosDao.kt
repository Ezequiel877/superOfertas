package com.example.kampasmobil2.Core.Utils

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kampasmobil2.Core.Result

/*interface ProductosDao {
    @Query("SELECT * FROM DataProductoEntity")
    suspend fun getProductosCarrito():Result<List<DataProductoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProductCarrito(producto: DataProductoEntity)
}*/