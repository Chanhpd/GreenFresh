package com.example.greenfresh.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.greenfresh.R
import com.example.greenfresh.model.Category

class CategoryProductAdapter(var cateList: ArrayList<Category>) :
    RecyclerView.Adapter<CategoryProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cate_product, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = cateList.get(position).name
    }

    override fun getItemCount(): Int {
        return cateList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_cate_pro)
    }
}