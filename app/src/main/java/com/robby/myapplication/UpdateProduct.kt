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

class UpdateProduct : AppCompatActivity() {
    companion object {
        const val EXTRA_ID : String = "Product ID: "
        const val EXTRA_NAME : String = "Product Name: "
        const val EXTRA_PRICE : String = "Product Price: "
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_product)
        title = "Update Product"

        val db  = Room.databaseBuilder(applicationContext, Database::class.java, "ProductDB").allowMainThreadQueries().build()
        val name : EditText = findViewById(R.id.edit_name)
        val price : EditText = findViewById(R.id.edit_price)
        val button : Button = findViewById(R.id.button_edit)
        val intent : Intent = intent

        name.setText(intent.getStringExtra(EXTRA_NAME))
        price.setText(intent.getStringExtra(EXTRA_PRICE))

        button.setOnClickListener {
            if (name.length() == 0 || price.length() == 0) {
                Toast.makeText(applicationContext, "Form Cannot Be Empty", Toast.LENGTH_SHORT).show()
            } else {
                val product = Product()
                product.id = intent.getStringExtra(EXTRA_ID)!!.toInt()
                product.name = name.text.toString()
                product.price = price.text.toString().toInt()
                db.productService().updateProduct(product)
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }
}