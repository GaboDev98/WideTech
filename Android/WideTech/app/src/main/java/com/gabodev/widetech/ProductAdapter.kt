package com.gabodev.widetech

import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import com.squareup.picasso.Picasso
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.product_item.view.*

class ProductAdapter(private val list: ArrayList<ProductResponse>): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    inner class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(productResponse: ProductResponse) {
            with(itemView) {
                Picasso.with(itemView.context)
                    .load(productResponse.imageUrl)
                    .placeholder(R.drawable.loading)
                    .fit()
                    .into(imageView)
                tvName.text = productResponse.name
                tvDescription.text = productResponse.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(list[position])
    }
}