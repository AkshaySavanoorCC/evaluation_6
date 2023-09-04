package com.calculator.evaluation_4.adopter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.calculator.evaluation_4.dataModel.DataModel
import com.calculator.evaluation_4.R
import com.calculator.evaluation_4.databinding.FragmentReBinding
import com.google.android.material.card.MaterialCardView

class CustomAdopter(
    private val context: Context,
    private val offerList: List<DataModel>,
    private val onItemClick: (position: Int) -> Unit,
    private val buttonReference: Button
) : RecyclerView.Adapter<CustomAdopter.ViewHolder>() {

    class ViewHolder(offerView: View) : RecyclerView.ViewHolder(offerView) {
        val image: ImageView = offerView.findViewById(R.id.imageView)
        val offersText: TextView = offerView.findViewById(R.id.offersText)
        val offerId : MaterialCardView = offerView.findViewById(R.id.offerId);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = offerList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val adopterClass = offerList[position]



        holder.offersText.text = context.resources.getString(adopterClass.offer)
        holder.image.setImageResource(adopterClass.imageItem)

        val backgroundColor = if (adopterClass.isSelected) {
            ContextCompat.getColor(context, android.R.color.white)
        } else {
            ContextCompat.getColor(context, R.color.default_card_color)
        }
        holder.itemView.setBackgroundColor(backgroundColor)

        val chkImg = holder.itemView.findViewById<ImageView>(R.id.checkImg)
        chkImg.visibility = if (adopterClass.isSelected) View.VISIBLE else View.GONE

      if (chkImg.visibility == View.VISIBLE){
          holder.offerId.strokeColor = Color.parseColor("#EE8664")
          holder.offerId.strokeWidth = context.resources.getDimensionPixelSize(R.dimen.stroke_width)
      }
        else
      {
          holder.offerId.strokeColor = Color.TRANSPARENT
          holder.offerId.strokeWidth = 0
      }
        val selectedOffer = offerList.count{it.isSelected}
        buttonReference.visibility = if (selectedOffer > 0) View.VISIBLE else View.GONE
        holder.itemView.setOnClickListener {

            offerList[position].isSelected = !offerList[position].isSelected
            onItemClick(position)

            val newBackgroundColor = if (adopterClass.isSelected) {
                ContextCompat.getColor(context, R.color.white)
            } else {
                ContextCompat.getColor(context, R.color.default_card_color)
            }
            holder.itemView.setBackgroundColor(newBackgroundColor)
        }
    }
}
