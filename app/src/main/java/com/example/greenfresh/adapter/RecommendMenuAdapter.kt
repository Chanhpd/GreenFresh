package com.example.greenfresh.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.greenfresh.R
import com.example.greenfresh.model.RecommendMenu

class RecommendMenuAdapter(var context: Context, var productList: List<RecommendMenu>) :
    RecyclerView.Adapter<RecommendMenuAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_name_menu)
        var tv_calo_menu : TextView = itemView.findViewById(R.id.tv_calo_menu)
        var pic: ImageView = itemView.findViewById(R.id.picCart)
        var tv_unit_menu : TextView = itemView.findViewById(R.id.tv_unit_menu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recom_menu, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = productList.get(position).name
        holder.tv_calo_menu.text =  productList.get(position).calo.toString()+" calo"
        holder.tv_unit_menu.text = "Unit: "+  productList.get(position).unit.toString()
        Glide.with(holder.itemView.context).load(productList.get(position).thumb).into(holder.pic)
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}