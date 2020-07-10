package com.robby.myapplication.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Product {

    @PrimaryKey
    var id : Int = 0

    @ColumnInfo(name = "name")
    var name : String = ""

    @ColumnInfo(name = "price")
    var price : Int = 0
}