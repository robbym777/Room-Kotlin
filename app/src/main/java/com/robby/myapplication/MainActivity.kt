package com.robby.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.robby.myapplication.room.Database
import com.robby.myapplication.room.Product
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ProductAdapter.OnClickListener {
    companion object {const val EDIT_NOTE_REQUEST : Int = 1}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = Room.databaseBuilder(applicationContext, Database::class.java, "ProductDB").allowMainThreadQueries().build()
        val recyclerView : RecyclerView = findViewById(R.id.rec_view)
        val get : List<Product> =db.productService().getAllProduct()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ProductAdapter(this, get)
        fab.setOnClickListener {
            startActivity(Intent(this, CreateProduct::class.java))
        }
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }
            override fun onSwiped(target: RecyclerView.ViewHolder, direction: Int) {
                val position : Int = target.adapterPosition
                val rows : Product = ProductAdapter(this@MainActivity, get).getProductAt(position)
                db.productService().deleteProduct(rows)
                ProductAdapter(this@MainActivity, get).notifyDataSetChanged()
                startActivity(Intent(applicationContext, MainActivity::class.java))
                Toast.makeText(applicationContext, "Employee Deleted", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recyclerView)
    }

    override fun onClick(prod: Product) {
        val intent = Intent(this, UpdateProduct::class.java)
        intent.putExtra(UpdateProduct.EXTRA_ID, prod.id.toString())
        intent.putExtra(UpdateProduct.EXTRA_NAME, prod.name)
        intent.putExtra(UpdateProduct.EXTRA_PRICE, prod.price.toString())
        startActivityForResult(intent, EDIT_NOTE_REQUEST)
    }
}
