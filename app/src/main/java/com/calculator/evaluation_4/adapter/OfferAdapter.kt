package com.calculator.evaluation_4.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.calculator.evaluation_4.OfferDatabase.OfferEntity
import com.calculator.evaluation_4.R

import com.calculator.evaluation_4.databinding.CardViewBinding
import com.google.android.material.card.MaterialCardView


class OfferAdapter(
    private val context: Context,
    private val offerList: List<OfferEntity>,
    private val onItemClick: (position: Int) -> Unit,
) : RecyclerView.Adapter<OfferAdapter.ViewHolder>() {

    class ViewHolder(binding: CardViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val offerContainer: MaterialCardView = binding.recyclerCardView
        val offersText: TextView = binding.offersText
        val checkImage: ImageView = binding.checkImg
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: CardViewBinding = CardViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = offerList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentAdopterItem = offerList[position]

        holder.offersText.text = currentAdopterItem.offerName

        if(currentAdopterItem.isSelected ){
            holder.itemView.setBackgroundColor( ContextCompat.getColor(context, android.R.color.white))
            holder.checkImage.visibility = View.VISIBLE
            holder.offerContainer.strokeColor = Color.parseColor("#EE8664")
            holder.offerContainer.strokeWidth = context.resources.getDimensionPixelSize(R.dimen.stroke_width)

        }
        else{
            holder.itemView.setBackgroundColor( ContextCompat.getColor(context, R.color.default_card_color))
            holder.checkImage.visibility = View.GONE
            holder.offerContainer.strokeColor = Color.TRANSPARENT
            holder.offerContainer.strokeWidth = 0
        }

        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
    }
}
