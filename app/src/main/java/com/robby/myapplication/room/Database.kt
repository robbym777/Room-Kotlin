package com.robby.myapplication.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(Product::class)], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun productService() : Service
}