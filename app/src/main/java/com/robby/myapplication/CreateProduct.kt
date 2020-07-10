package com.robby.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.robby.myapplication.room.Database
import com.robby.myapplication.room.Product

class CreateProduct: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_product)
        title = "Create Product"
        val db : Database = Room.databaseBuilder(applicationContext, Database::class.java, "ProductDB").allowMainThreadQueries().build()
        val id : EditText = findViewById(R.id.field_id)
        val name : EditText = findViewById(R.id.field_name)
        val price : EditText = findViewById(R.id.field_price)
        val button : Button = findViewById(R.id.button_submit)
        button.setOnClickListener {
            if(id.length() == 0 || name.length() == 0 || price.length() == 0){
                Toast.makeText(applicationContext, "Form Cannot Be Empty", Toast.LENGTH_SHORT).show()
            } else {
                val product = Product()
                product.id = id.text.toString().toInt()
                product.name = name.text.toString()
                product.price = price.text.toString().toInt()
                db.productService().createProduct(product)
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }
}