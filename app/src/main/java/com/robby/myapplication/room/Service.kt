package com.robby.myapplication.room

import androidx.room.*
import androidx.room.Dao

@Dao
interface Service {
    @Insert
    fun createProduct(emp: Product)

    @Query(value = "SELECT * FROM Product")
    fun getAllProduct() : List<Product>

    @Delete
    fun deleteProduct(emp: Product)

    @Update
    fun updateProduct(emp: Product)
}