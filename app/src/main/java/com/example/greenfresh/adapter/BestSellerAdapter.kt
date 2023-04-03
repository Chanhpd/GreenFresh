package com.example.greenfresh.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.greenfresh.R
import com.example.greenfresh.activity.DetailProductActivity
import com.example.greenfresh.model.Product

class BestSellerAdapter(var context: Context, var productList: List<Product>) :
    RecyclerView.Adapter<BestSellerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_title_seller)
        var tvSale : TextView = itemView.findViewById(R.id.tv_sale_seller)
        var pic: ImageView = itemView.findViewById(R.id.pic_seller)
        var price: TextView = itemView.findViewById(R.id.tv_price_seller)
        var unit : TextView = itemView.findViewById(R.id.tv_unit_seller)
        var addBtn: ImageView = itemView.findViewById(R.id.addBtn_seller)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_best_seller, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = productList.get(position).name
        holder.price.text = "$" + productList.get(position).price.toString()
        holder.tvSale.text =   productList.get(position).sale.toString()+"%"
        holder.unit.text = "Unit: "+productList.get(position).unit
        Glide.with(holder.itemView.context).load(productList.get(position).thumb).into(holder.pic)
        holder.itemView.setOnClickListener {
            val i = Intent(context,DetailProductActivity::class.java)
            i.putExtra("id",productList.get(position).id)
            context.startActivity(i)
        }
// btn
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}