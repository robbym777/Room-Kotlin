package com.robby.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.robby.myapplication.room.Product

class ProductAdapter(private var listener: OnClickListener, private var product : List<Product>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.product_row, parent, false)
        return ViewHolder(view, listener, product)
    }

    override fun getItemCount(): Int {
        return product.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = product[position].id.toString()
        holder.name.text = product[position].name
        holder.price.text = product[position].price.toString()
        holder.update
    }

    class ViewHolder(itemView : View, private val listener: OnClickListener, private val product: List<Product>): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val id :TextView = itemView.findViewById(R.id.employee_row_id)
        val name : TextView = itemView.findViewById(R.id.employee_row_name)
        val price : TextView = itemView.findViewById(R.id.employee_row_price)
        val update = itemView.setOnClickListener{
            onClick(itemView)
        }
        override fun onClick(v: View?) {
            val position : Product = product[adapterPosition]
            if(adapterPosition != RecyclerView.NO_POSITION){
                listener.onClick(position)
            }
        }
    }

    fun getProductAt(position: Int) : Product {
        return product[position]
    }
    interface OnClickListener {
        fun onClick(prod : Product)
    }
    fun setOnCLickListener(listener : OnClickListener) {
        this.listener = listener
    }
}