package com.calculator.evaluation_4.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.calculator.evaluation_4.OfferDatabase.OfferEntity
import com.calculator.evaluation_4.databinding.CardViewBinding

class SelectedOfferAdapter(private val context: Context, private val offerList: List<OfferEntity>) :
    RecyclerView.Adapter<SelectedOfferAdapter.ViewHolder>() {

    class ViewHolder(binding: CardViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val offersText = binding.offersText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: CardViewBinding = CardViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val adopterClass = offerList[position]
        holder.offersText.text = adopterClass.offerName

    }
    override fun getItemCount() = offerList.size

}