package com.example.greenfresh.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.greenfresh.R
import com.example.greenfresh.model.Category

class CategoryAdapter(var cateList: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = cateList.get(position).name

        Glide.with(holder.itemView.context).load(cateList.get(position).pic).into(holder.pic)
    }

    override fun getItemCount(): Int {
        return cateList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cateLayout: ConstraintLayout = itemView.findViewById(R.id.cateLayout)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title_cate)
        val pic: ImageView = itemView.findViewById(R.id.img_pic_cate)
    }
}