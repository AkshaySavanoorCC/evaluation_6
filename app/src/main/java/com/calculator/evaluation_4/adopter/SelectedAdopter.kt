package com.calculator.evaluation_4.adopter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.calculator.evaluation_4.R
import com.calculator.evaluation_4.dataModel.DataModel

class SelectedAdopter(private val context: Context, private val offerList: List<DataModel>) :
    RecyclerView.Adapter<SelectedAdopter.ViewHolder>() {

    class ViewHolder(offerView: View) : RecyclerView.ViewHolder(offerView) {
        val image: ImageView = offerView.findViewById(R.id.imageView);
        val offersText: TextView = offerView.findViewById(R.id.offersText);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val adopterClass = offerList[position]
        holder.offersText.text = context.resources.getString(adopterClass.offer)
        holder.image.setImageResource(adopterClass.imageItem);
    }

    override fun getItemCount() = offerList.size;

}